let fi = document.getElementById('regfirstname')
let e = document.getElementById('regemail')
let us = document.getElementById('regusername')
let ps = document.getElementById('regpassword')
let regform = document.getElementById('regform')
let regErrorElement = document.getElementById('regerror')

alert("registration.js is valid");

regform.addEventListener('submit', (submit) => {
    let messages = [];

    if((fi.value.trim() == null) || (fi.value.trim() == "")) {
        messages.push('Firstname is required');
        fi.classList.add("is-invalid");
    }
    else {
        fi.classList.remove("is-invalid")
        fi.classList.add("is-valid");
    }

    if(e.value.trim() == null || (e.value.trim() == "")) {
        messages.push('Email is required');
        e.classList.add("is-invalid");
    }
    else {
        if (e.value.includes("@")) {
            e.classList.remove("is-invalid")
            e.classList.add("is-valid");
        }
        else {
            messages.push('Real email is required');
            e.classList.add("is-invalid");
        }
    }


    if(us.value.trim() == null || (us.value.trim() == "")) {
        messages.push('Username is required');
        us.classList.add("is-invalid");
    }
    else {
        us.classList.remove("is-invalid")
        us.classList.add("is-valid");
    }

    if(ps.value.trim().length < 6) {

        if ((ps.value.trim() == null) || (ps.value.trim() == "")) {
            messages.push('Password is required');
            ps.classList.add("is-invalid");
        } else {
            messages.push('Password must be longer than 5 characters')
            ps.classList.add("is-invalid");
        }

    }
    else {
        ps.classList.remove("is-invalid")
        ps.classList.add("is-valid");
    }

    if (messages.length > 0) {
        submit.preventDefault()
        regErrorElement.classList.add("text-danger")
        regErrorElement.innerHTML = messages.join(', ')

    }
})
