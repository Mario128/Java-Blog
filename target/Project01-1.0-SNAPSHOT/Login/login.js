const u = document.getElementById('logusername')
const p = document.getElementById('logpassword')
const logform = document.getElementById('logform')
const errorElement = document.getElementById('logerror')


logform.addEventListener('submit', (submit) => {
    let messages = []

    if((u.value.trim() == null) || (u.value.trim() == "")) {
        messages.push('Name is required')
        u.classList.add("is-invalid");
    }
    else {
        u.classList.remove("is-invalid");
        u.classList.add("is-valid");
    }

    if(u.value.trim().length > 10) {
        messages.push('Name must be shorter than 10 characters')
    }
    if(p.value.trim().length < 6) {

        p.classList.add("is-invalid")

        if((p.value.trim() == null) || (p.value.trim() == "")) {
            messages.push('Password is required')
        }
        else {
            messages.push('Password must be longer than 6 characters')
        }
    }
    else {
        p.classList.remove("is-invalid");
        p.classList.add("is-valid");
    }

    if(messages.length > 0 ) {
        submit.preventDefault()

        /*    for(let i = 0; i < messages.length; i++) {
        errorElement.innerHTML += '<p>' + messages[i] + '</p>'
        }
        */
        errorElement.className = "text-danger"
        errorElement.innerHTML = messages.join(', ')

    }

} )
