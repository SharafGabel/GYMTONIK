
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String title = "Bienvenue"; %>
<%@ include file="header.jsp" %>

    <div class="container">
        <div class="row">
            <div class="col-md-4"></div>
           
            <div class="col-md-4 center" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                <form id="formRegister" class="form-horizontal" name="formRegister" method="post" action="../RegisterServlet" >
                    <h1>S'inscrire</h1>
    
                    <label>Nom d'utilisateur*</label>
                    <input class="form-control" type="text" name="username" id="user" placeholder="Nom d'utilisateur"/>
    
                    <label>E-Mail*</label>
                    <input class="form-control" type="text" name="email" id="email" placeholder="Saisissez votre e-mail" />
    
                    <label>Confirmation E-Mail*</label>
                    <input class="form-control" type="text" name="emailVerif" id="emailVerif" placeholder="Confirmez votre e-mail"/>
    
                    <label>Mot de passe*</label>
                    <input class="form-control" type="password" name="password" id="pass" placeholder="Entrez votre mot de passe" />
    
                    <label>Taille (en cm)</label>
                    <input class="form-control" type="number" name="height" id="height" placeholder="Entrez votre taille"/>
    
                    <label>Poids (en kg)</label>
                    <input class="form-control" type="number" name="weight" id="weight" placeholder="Entrez votre poids" />
    
                    <p><button class="btn btn-small btn-success" style="margin-top: 4px;" type="submit">Register</button>
    
                    <label>* (Obligatoire)</label>
                </form>
            </div>
            <div class="col-md-4"></div>
    </div>
        </div>

<script src="../assets/js/jquery-1.8.2.min.js"></script>
<script src="../assets/js/scripts.js"></script>

<%@ include file="../footer.jsp" %>