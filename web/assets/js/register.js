/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


// Import the functions you need from the SDKs you need
import { initializeApp } from "https://www.gstatic.com/firebasejs/9.22.1/firebase-app.js";
import { getAnalytics } from "https://www.gstatic.com/firebasejs/9.22.1/firebase-analytics.js";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries
import { getAuth, createUserWithEmailAndPassword, sendEmailVerification  } from 'https://www.gstatic.com/firebasejs/9.22.1/firebase-auth.js'
        $(document).ready(function () {
    // Your web app's Firebase configuration
    // For Firebase JS SDK v7.20.0 and later, measurementId is optional
    const firebaseConfig = {
        apiKey: "AIzaSyC9PV_Kcz009hxrjYM7VRUTc_90cu--zCQ",
        authDomain: "cinemagic-388114.firebaseapp.com",
        projectId: "cinemagic-388114",
        storageBucket: "cinemagic-388114.appspot.com",
        messagingSenderId: "888385144954",
        appId: "1:888385144954:web:dc08cad3bd55b6c6cc53c3",
        measurementId: "G-5670FQZMDC"
    };

// Initialize Firebase
    const app = initializeApp(firebaseConfig);
    const auth = getAuth(app);
    console.log(auth);

    const  register = document.getElementById("register");
    register.addEventListener("submit", (e) => {
        e.preventDefault();

        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        createUserWithEmailAndPassword(auth, email, password).then(
                (cred) => {
            const user = auth.currentUser;
            sendEmailVerification(auth.currentUser).then(() => {
                $.ajax({
                    type: "POST",
                    url: "http://localhost:8080/register?" + 'txtEmail=' + email + '&txtPassword=' + password,
                    data: {},
                    dataType: "json",
                    encode: true
                }).done(function (data) {
                    console.log(data);
                });
            })

                    ;

        });
    });
});