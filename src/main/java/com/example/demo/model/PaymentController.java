package com.example.demo.model;


import com.example.demo.daos.UserRepository;
import com.example.demo.entities.User;
import com.example.demo.metier.ICommandeMetier;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.model.issuing.Card;
import com.stripe.model.terminal.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import static com.example.demo.contollers.ControllerApp.priceTotal;

@Controller
public class PaymentController {

    @Autowired
    ICommandeMetier commandeMetier;
    @Autowired
    UserRepository userRepository;
    @Value("${stripe.keys.public}")
    private String API_PUBLIC_KEY;

    private StripeService stripeService;

    public PaymentController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @GetMapping("/")
    public String homepage() {
        return "homepage";
    }

    @GetMapping("/subscription")
    public String subscriptionPage(Model model) {
        model.addAttribute("stripePublicKey", API_PUBLIC_KEY);
        return "subscription";
    }

    @RequestMapping(value = "/validerCommande")
    public String validerCommande(Model mode, HttpServletRequest request) {
        String  user = (String)request.getSession().getAttribute("username");
        if(user == null){
            return "login";
        }
        mode.addAttribute("stripePublicKey", API_PUBLIC_KEY);
        if(priceTotal != 0){
            mode.addAttribute("priceTotal",priceTotal);
        }
        return "validerCommande";
    }

    @GetMapping("/charge")
    public String chargePage(Model model) {
        model.addAttribute("stripePublicKey", API_PUBLIC_KEY);
        return "charge";
    }

    /*========== REST APIs for Handling Payments ===================*/

    @PostMapping("/create-subscription")
    public @ResponseBody
    Response createSubscription(String email, String token, String plan, String coupon) {
        //validate data
        if (token == null || plan.isEmpty()) {
            return new Response(false, "Stripe payment token is missing. Please, try again later.");
        }

        //create customer first
        String customerId = stripeService.createCustomer(email, token);

        if (customerId == null) {
            return new Response(false, "An error occurred while trying to create a customer.");
        }

        //create subscription
        String subscriptionId = stripeService.createSubscription(customerId, plan, coupon);
        if (subscriptionId == null) {
            return new Response(false, "An error occurred while trying to create a subscription.");
        }



        // Ideally you should store customerId and subscriptionId along with customer object here.
        // These values are required to update or cancel the subscription at later stage.

        return new Response(true, "Success! Your subscription id is " + subscriptionId);
    }

    @PostMapping("/cancel-subscription")
    public @ResponseBody
    Response cancelSubscription(String subscriptionId) {
        boolean status = stripeService.cancelSubscription(subscriptionId);
        if (!status) {
            return new Response(false, "Failed to cancel the subscription. Please, try later.");
        }
        return new Response(true, "Subscription cancelled successfully.");
    }

    @PostMapping("/coupon-validator")
    public @ResponseBody
    Response couponValidator(String code) {
        Coupon coupon = stripeService.retrieveCoupon(code);
        if (coupon != null && coupon.getValid()) {
            String details = (coupon.getPercentOff() == null ? "$" + (coupon.getAmountOff() / 100) : coupon.getPercentOff() + "%") +
                    " OFF " + coupon.getDuration();
            return new Response(true, details);
        } else {
            return new Response(false, "This coupon code is not available. This may be because it has expired or has " +
                    "already been applied to your account.");
        }
    }

    @PostMapping("/create-charge")
    public @ResponseBody
    Response createCharge(String email, String token,String amount,String adress,String city, String phone,HttpServletRequest request) throws StripeException {
        //validate data
        if (token == null) {
            return new Response(false, "Stripe payment token is missing. Please, try again later.");
        }

        //create charge
        String chargeId = stripeService.createCharge(email, token, (int)Double.parseDouble(amount.split("MAD")[0]) * 100); //$9.99 USDg
        if (chargeId == null) {
            return new Response(false, "An error occurred while trying to create a charge.");
        }
        request.getSession().setAttribute("charge_id",chargeId);
        Address address = new Address();
        address.setLine1(adress);
        address.setCity(city);
        PaymentMethod.BillingDetails myBill = new PaymentMethod.BillingDetails();
        myBill.setPhone(phone);
        myBill.setAddress(address);
        Charge charge;
        charge = Charge.retrieve(chargeId);
        charge.setBillingDetails(myBill);//

        User myuser = userRepository.findByusername((String) request.getSession().getAttribute("username"));
        int idCommand = commandeMetier.ValiderCommande(myuser.getPanier().getId(),myuser,true,adress+", "+city.toUpperCase());
        request.getSession().setAttribute("myCurrentCommande",idCommand);

        // You may want to store charge id along with order information

        return new Response(true, /*"Success! Your charge id is " + */chargeId.substring(0,18));
    }
}