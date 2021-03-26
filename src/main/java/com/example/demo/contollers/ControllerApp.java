package com.example.demo.contollers;

import com.example.demo.dao.ICategorieImpl;
import com.example.demo.dao.IPanierImpl;
import com.example.demo.daos.UserRepository;
import com.example.demo.entities.*;
import com.example.demo.metier.*;
import com.example.demo.model.PaymentIntentDTO;
import com.example.demo.model.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
@Controller
public class ControllerApp {
    String value;
    static int flag=0;
    int nbre;

    @Autowired
    IPanierMetier panierMetier;
    @Autowired
    IProduitImplMetier prod;

    @Autowired
    ICategorieImpl catg;

    @Autowired
    IComposantsPanierImplMetier comp;

    @Autowired
    IPanierImpl pan;
	@Autowired
    IUserMetier userMetier;
	@Autowired
    HASH hash;
	@Autowired
    IEmail emailMetier;
	@Autowired
    UserRepository userRepository;
	@Autowired
    IComposantsPanierImplMetier composantsPanierImplMetier;
	@Autowired
    PaymentService paymentService;
	@Autowired
    ICommandeMetier iCommandeMetier;


    int code = 0;
    private String firstname,lastname,email,username,pwd,address,cin,phone,role;
    public static double priceTotal = 0;

    @RequestMapping(value = "/suppCart", method = RequestMethod.POST)
    public String requestMethodName(@RequestParam String supp,Model mode,HttpServletRequest request) {
        if(request.getMethod().equals(RequestMethod.GET)){
            return "login";
        }
        int num = Integer.valueOf(supp);

        comp.SupprimerComposantPanier(num);


        flag=1;



        return "redirect:/home";
    }

    @RequestMapping(value = "/suppCart", method = RequestMethod.GET)
    public String requestMethodGet() {
            return "login";
    }

    @RequestMapping(value = "/MyAccount")
    public String MyProfile(Model mode,HttpServletRequest request) {

        if(request.getSession().getAttribute("username")!=null) {

            User user = userRepository.findByusername((String) request.getSession().getAttribute("username"));

            mode.addAttribute("user", user);

            return "MyAccount";

        }
        else
        {

            return "login";
        }
    }



    @RequestMapping(value = "/edituser", method = RequestMethod.POST)
    public String EditProfile(Model model,@RequestParam(name = "firstname") String firstname, @RequestParam(name = "lastname") String lastname, @RequestParam(name = "email") String email, @RequestParam(name = "username") String username, @RequestParam(name = "pwd") String pwd, @RequestParam(name = "address") String address, @RequestParam(name = "cin") String cin,@RequestParam(name = "phone") String phone) {

        User user = userRepository.findByusername(username);
        user.setNom(lastname);
        user.setPrenom(firstname);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(pwd);
        user.setAdresse(address);
        user.setCin(cin);
        user.setNtelephone(phone);
        user.setRole("client");

        userMetier.EditUser(user, user.getIdClient());



        return "redirect:/MyAccount";

    }

    @PostMapping(value = "/paymentMethod")
    public ResponseEntity<String> paymentMethod(@RequestBody PaymentIntentDTO paymentIntentDTO) {
        try {
            PaymentIntent paymentIntent = paymentService.paymentIntent(paymentIntentDTO);
            String paymentStr = paymentIntent.toJson();
            return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
        } catch (StripeException e) {}

        return null;

    }

    @PostMapping(value = "/confirmPayment/{id}")
    public ResponseEntity<String> confirmPayment(@PathVariable String  id) {
        try {
            PaymentIntent paymentIntent = paymentService.confirm(id);
            String paymentStr = paymentIntent.toJson();
            return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
        } catch (StripeException e) {}

        return null;

    }
    @PostMapping(value = "/cancelPayment/{id}")
    public ResponseEntity<String> cancelPayment(@PathVariable String  id) {
        try {
            PaymentIntent paymentIntent = paymentService.cancel(id);
            String paymentStr = paymentIntent.toJson();
            return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
        } catch (StripeException e) {}

        return null;

    }

    @RequestMapping(value = "/register")
    public String home(Model mode) {

        return "register";
    }
    @RequestMapping(value = "/login")
    public String login(Model mode) {

        return "login";
    }
    @RequestMapping(value = "/myFacture")
    public String myFacture(Model mode,HttpServletRequest request) {
        Integer sessionCmd =  (Integer)request.getSession().getAttribute("myCurrentCommande");
        if(sessionCmd == null) return "login";

        Commande myCommande = iCommandeMetier.ChargerCommande (sessionCmd);
        mode.addAttribute("user_name",myCommande.getUser().getPrenom() +" "+myCommande.getUser().getNom());
         mode.addAttribute("adress",myCommande.getLocalisation());
         mode.addAttribute("phone",myCommande.getUser().getEmail());
         mode.addAttribute("commande_id",myCommande.getIdCommande());
         mode.addAttribute("date_commande",myCommande.getDateCommande());
         mode.addAttribute("charge_id",((String)request.getSession().getAttribute("charge_id")).substring(0,20));
         mode.addAttribute("total_price",priceTotal);
         mode.addAttribute("composates",myCommande.getComposante());
        return "myFacture";
    }


    @RequestMapping(value = "/home")
    public String Home(Model mode, HttpServletRequest request, HttpServletResponse response) {
        if(flag==0) {

            mode.addAttribute("dropdown","dropdown");

        }else {
            mode.addAttribute("dropdown","dropdown open");
            flag=0;
        }
        List<Produit> lst= prod.listeproduits();

        List<Produit> telephones =prod.produitsParCategorie(1);
        List<Produit> pc = prod.produitsParCategorie(2);
        List<Produit> camera=prod.produitsParCategorie(3);
        List<Produit> access=prod.produitsParCategorie(4);
        List<Categorie> cat =catg.listeCategories();
        mode.addAttribute("categories",cat);
        mode.addAttribute("listeProd", lst);
        mode.addAttribute("pc",pc);
        mode.addAttribute("camera",camera);
        mode.addAttribute("access",access);
        mode.addAttribute("telephones",telephones);

        Cookie[] cookies = request.getCookies();
        Cookie coka = null;
        if (cookies != null)
        for (Cookie cookie:
                cookies) {
            if(cookie.getName().equals("IdentifiantEcommerce")){
                coka = cookie;
                break;
            }
        }

        User user = userRepository.findByusername((String) request.getSession().getAttribute("username"));
        Panier panier = null;
        if(coka != null){//no user and we have cookies
             panier = panierMetier.FindPanier(Integer.parseInt(coka.getValue()));

        }else if(user != null){//no cookies and we have a user

             panier = panierMetier.FindPanier(user.getPanier().getId());
        }

        List<ComposantsPanier> composantsPaniers = null;
        if(panier != null) {
            composantsPaniers = panier.getComponents();

        double  sum = 0;
        for (ComposantsPanier cp:composantsPaniers) {
            sum = sum + cp.getProduit().getPrix() * cp.getQuantite();
        }
        mode.addAttribute("composantePanier",composantsPaniers);
        mode.addAttribute("lasomme",sum);
            priceTotal = sum;
        if(composantsPaniers == null){
            mode.addAttribute("sizeof",0);
        }else {
            mode.addAttribute("sizeof",composantsPaniers.size());
        }
        }else {
            mode.addAttribute("sizeof",0);
            mode.addAttribute("lasomme",0);
        }
        return "index";
    }


    @RequestMapping(value = "/addCart", method = RequestMethod.POST)
    public String Cart(@RequestParam String enrg, HttpServletRequest request, HttpServletResponse response) {

        int a = Integer.valueOf(enrg);
        Integer numPanier = null;
        User user = userRepository.findByusername((String) request.getSession().getAttribute("username"));
        if(user != null)
         numPanier = user.getPanier().getId();

        int y=0;
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
        {

            for (int i = 0; i < cookies.length; i++) {


                if(cookies[i].getName().equals("IdentifiantEcommerce")) {

                    y++;
                    value = cookies[i].getValue();

                    break;
                }

            }

        }
        Panier b = null;
        if(y==0 && numPanier == null) {
            Panier pn = new Panier();
            pn.setDateAjout(new Date());
            pan.Ajouterpanier(pn);
             b=pan.LastPanier();
            if (user == null) {
                Cookie cookie = new Cookie("IdentifiantEcommerce", String.valueOf(b.getId()));
                response.addCookie(cookie);
                ComposantsPanier cP = new ComposantsPanier();
                cP.setPanier(b);
                cP.setProduit(prod.getProduit(a));
                cP.setQuantite(1);
                comp.AjouterComposantPanier(cP, pan.FindPanier(b.getId()));
            }else {
                ComposantsPanier cP = new ComposantsPanier();
                cP.setPanier(b);
                cP.setProduit(prod.getProduit(a));
                cP.setQuantite(1);
                comp.AjouterComposantPanier(cP, pan.FindPanier(b.getId()));

                user.setPanier(b);
                userMetier.ajouterUser(user);
            }

        }else {
            int compt=0;
            Panier pn = null;
            if(y != 0)
            pn = pan.FindPanier(Integer.valueOf(value));
            else if(user != null){
                pn = pan.FindPanier(user.getPanier().getId());
            }
            for (int i = 0; i < pn.getComponents().size(); i++) {

                if(pn.getComponents().get(i).getProduit().getIdProduit()==prod.getProduit(a).getIdProduit()) {
                    compt=compt+1;
                    nbre=pn.getComponents().get(i).getId();
                    break;
                }

            }

            if(compt==0) {

                ComposantsPanier cP = new ComposantsPanier();
                cP.setPanier(pn);
                cP.setProduit(prod.getProduit(a));
                cP.setQuantite(1);
                comp.AjouterComposantPanier(cP, pn);

            }else {

                ComposantsPanier cP = comp.findComposant(nbre);

                cP.setQuantite(cP.getQuantite()+1);

                comp.ModifierComposantPanier(cP, nbre);

            }


        }


        return "redirect:/home";

    }


    @PostMapping(value = "/addUser")
    public String addUser(Model model,@RequestParam(name = "firstname") String firstname, @RequestParam(name = "lastname") String lastname, @RequestParam(name = "email") String email
            , @RequestParam(name = "username") String username, @RequestParam(name = "pwd") String pwd, @RequestParam(name = "address") String address,
    @RequestParam(name = "cin") String cin,@RequestParam(name = "phone") String phone,@RequestParam(name = "role") String role) {


         int flag = 0;

        List<User> userList = userMetier.listeclients();

        for (User user: userList) {
            if(user.getUsername().equals(username) || user.getEmail().equals(email)){
                if(user.getEmail().equals(email)){

                    flag = 1;
                    break;
                }else {
                    flag = 2;
                    break;
                }
            }
        }

        if (flag == 1){
            model.addAttribute("error",email + " deja exisit !");
        }else if (flag == 2){
            model.addAttribute("error",username + " deja exisit !");
        }else {

            //la verification before

                code = emailMetier.sendEmailToUser(email, firstname);

                this.firstname = firstname;
                this.lastname = lastname;
                this.email = email;
                this.username = username;
                this.pwd = pwd;
                this.address =address;
                this.cin = cin;
                this.phone = phone;
                this.role = role;

                return "verificationPage";

        }

        return "register";
    }

    @PostMapping(value = "/loginUser")
    public String loginUser(Model model, HttpServletRequest  request,HttpServletResponse response, @RequestParam(name = "username") String username, @RequestParam(name = "pwd") String pwd){
         int flag = 0;
         User userWithPanier = null;
        List<User> userList = userMetier.listeclients();

        for (User user: userList) {
            if(user.getUsername().equals(username) && hash.getMd5(pwd).equals(user.getPassword())){
                    flag = 1;
                    userWithPanier = user;
                    break;
            }
        }

        if(flag == 0){
            model.addAttribute("error","Your Username or Password is incorrect !");
            return "login";
        }else{

                request.getSession().setAttribute("username",username);
                request.getSession().setAttribute("pwd",pwd);
                Cookie[] cookies = request.getCookies();
                Cookie coka = null;
                for (Cookie cookie:
                        cookies) {
                    if(cookie.getName().equals("IdentifiantEcommerce")){
                        coka = cookie;
                        break;
                    }
                }
                if(coka !=null ){
                    if(userWithPanier.getPanier() == null){
                        int valueCoka = Integer.parseInt(coka.getValue());
                        User user = userRepository.findByusername(username);
                        user.setPanier(panierMetier.FindPanier(valueCoka));
                        coka.setMaxAge(0);
                        response.addCookie(coka);
                        userMetier.ajouterUser(user);
                    }else{
                        Panier oldPanier = userWithPanier.getPanier();
                        Panier cokaPanier  = panierMetier.FindPanier(Integer.parseInt(coka.getValue()));
                        for (ComposantsPanier composantsPanier:cokaPanier.getComponents()) {
                            composantsPanier.setPanier(oldPanier);
                            composantsPanierImplMetier.AjouterComposantPanier(composantsPanier,oldPanier);
                        }
                        panierMetier.SupprimerPanier(cokaPanier.getId());
                        coka.setMaxAge(0);
                        response.addCookie(coka);
                    }
                }

            return "redirect:/home";
        }


    }
    @PostMapping(value = "/verifyUser")
    public String verifyUser(Model model,@RequestParam(name = "code") String code){

         if(!code.equals("") && Integer.parseInt(code) == this.code){

             try {
                 userMetier.ajouterUser(new User(true,lastname,firstname,cin,phone,email,username,hash.getMd5(pwd),address,role));
                 model.addAttribute("error","salue "+lastname + "!");
             }catch (Exception e){
                 model.addAttribute("error","Error d'enregistrement !");
             }
         }else{
             model.addAttribute("error","Verification code is failed !");
         }

       return "register";
    }
    
    
    @GetMapping("/international")
    public String getInternationalPage() {
        return "international";
    }
/*
 @RequestMapping(value = "/home")
    public String Home(Model mode, HttpServletRequest request, HttpServletResponse response) {
        if(flag==0) {

            mode.addAttribute("dropdown","dropdown");

        }else {
            mode.addAttribute("dropdown","dropdown open");
            flag=0;
        }
        List<Produit> lst= prod.listeproduits();

        List<Produit> telephones =prod.produitsParCategorie(1);
        List<Produit> pc = prod.produitsParCategorie(2);
        List<Produit> camera=prod.produitsParCategorie(3);
        List<Produit> access=prod.produitsParCategorie(4);
        List<Categorie> cat =catg.listeCategories();
        mode.addAttribute("categories",cat);
        mode.addAttribute("listeProd", lst);
        mode.addAttribute("pc",pc);
        mode.addAttribute("camera",camera);
        mode.addAttribute("access",access);
        mode.addAttribute("telephones",telephones);

        Cookie[] cookies = request.getCookies();
        Cookie coka = null;
        if (cookies != null)
        for (Cookie cookie:
                cookies) {
            if(cookie.getName().equals("IdentifiantEcommerce")){
                coka = cookie;
                break;
            }
        }

        User user = userRepository.findByusername((String) request.getSession().getAttribute("username"));
        Panier panier = null;
        if(coka != null){//no user and we have cookies
             panier = panierMetier.FindPanier(Integer.parseInt(coka.getValue()));

        }else if(user != null){//no cookies and we have a user

             panier = panierMetier.FindPanier(user.getPanier().getId());
        }

        List<ComposantsPanier> composantsPaniers = null;
        if(panier != null) {
            composantsPaniers = panier.getComponents();

        double  sum = 0;
        for (ComposantsPanier cp:composantsPaniers) {
            sum = sum + cp.getProduit().getPrix() * cp.getQuantite();
        }
        mode.addAttribute("composantePanier",composantsPaniers);
        mode.addAttribute("lasomme",sum);
            priceTotal = sum;
        if(composantsPaniers == null){
            mode.addAttribute("sizeof",0);
        }else {
            mode.addAttribute("sizeof",composantsPaniers.size());
        }
        }else {
            mode.addAttribute("sizeof",0);
            mode.addAttribute("lasomme",0);
        }
        return "index";
    }

*/
	
}
