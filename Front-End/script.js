function enviar() {
    var ehttp = new XMLHttpRequest();
    var mail = document.getElementById("mail").value;
    var password = document.getElementById("password").value;
    ehttp.open("GET", "http://localhost:3002/Farmacia/Login?mail="+mail+"&password="+password, true); 
    ehttp.send();
    ehttp.onreadystatechange= function(){
        if (this.readyState==4 && this.status==200){
            var session = ehttp.responseText;
            if(session!="0"){
                window.sessionStorage.setItem("mail",mail);
                window.sessionStorage.setItem("session", session);
                window.location.replace("gestio.html");
            }      
        }
    }
}

function logOut() {
    sessionStorage.removeItem("mail")
    sessionStorage.removeItem("session")
    window.location.href = "index.html";
}

function getTable() {
    var mail = sessionStorage.getItem('mail');
    var session = sessionStorage.getItem('session');
    var ehttp = new XMLHttpRequest();
    ehttp.open("GET", "http://localhost:3002/Farmacia/Servexips?mail="+mail+"&session="+session,true);
    ehttp.onreadystatechange = function() {
        if (ehttp.status == 200) {
            var HTML = ehttp.responseText;
            let contenidoTabla = document.getElementById("tabla");
            contenidoTabla.innerHTML = HTML;
            var boton = document.getElementById("botonCerrar");
            var tabla = document.getElementById("tabla");
            console.log("Elemento:", document.getElementById("botonCerrar"));
            boton.style.display = "flex";
            tabla.style.display = "flex";
            boton.addEventListener("click", function() {
                tabla.style.display = "none";
                boton.style.display = "none";
              });
        }
    }
    ehttp.send();
}

function goToAlta() {
    window.location.href = "alta.html";
}

function getPaciente(){
    var mail = sessionStorage.getItem('mail');
    var session = sessionStorage.getItem('session');

    let ehttp = new XMLHttpRequest();

    ehttp.open("GET","http://localhost:3002/Farmacia/Servepatients?mail="+mail+"&session="+session,true);
    ehttp.send();

    ehttp.onreadystatechange=function(){
        if(this.readyState==4 && this.status==200){
            var patients = JSON.parse(this.responseText);
            var select = document.getElementById("patientList");
            console.log(ehttp.responseText);

            select.innerHTML = "";

            patients.forEach(function(patient, index) {
                if (index % 2 === 0) {
                var option = document.createElement("option");
                option.text = patient;
                select.add(option);
            }});
        }
    }
}

function getMedicina(){
    var mail = sessionStorage.getItem('mail');
    var session = sessionStorage.getItem('session');

    let ehttp = new XMLHttpRequest();

    ehttp.open("GET","http://localhost:3002/Farmacia/Servemedicines?mail="+mail+"&session="+session,true);
    ehttp.send();

    ehttp.onreadystatechange=function(){
        if(this.readyState==4 && this.status==200){
            var medicines = JSON.parse(ehttp.responseText);
            console.log(ehttp.responseText);
            var select = document.getElementById("medicineList");

            select.innerHTML = "";

            medicines.forEach(function(medicine, index) {
                if (index % 2 === 0) {
                var option = document.createElement("option");
                option.text = medicine;
                select.add(option);
            }});
        }
    }
}

function enviar1() {
    var mail = sessionStorage.getItem("mail");
    var session = sessionStorage.getItem("session");
    var idXip = document.getElementById("xipId").value;
    var mailP = document.getElementById("patientList").value;
    var idMed = document.getElementById("medicineList").value;
    var date = document.getElementById("endDate").value;

    var ehttp = new XMLHttpRequest();

    console.log(idXip)
    console.log(mailP)
    console.log(idMed)
    console.log(date)

    ehttp.open("POST","http://localhost:3002/Farmacia/Release?mail="+mail+"&session="+session+ "&xipId="+idXip+"&patientList="+mailP+"&medicineList="+idMed+"&endDate="+date, true)

    ehttp.onload = function() {
        if (ehttp.status == 200) {
            var response = ehttp.responseText;
           
                console.info("XIP registrado");
                alert("XIP registrado correctamente");

                document.getElementById("idXip").value = "";
                document.getElementById("patientList").value = "";
                document.getElementById("medicineList").value = "";
                document.getElementById("endDate").value = "";

        } else {
            console.error("Error:", ehttp.status);
        }
    };
    ehttp.send();
}