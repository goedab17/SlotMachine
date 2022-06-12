var _jwt;
function login(name, pwd) {
    let user = {
        'name': name,
        'pwd': pwd
    };
    console.log(user)
    fetch("./api/login",{
        method:"POST",
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify(user)
    })
        .then(res =>{
            console.log(res.status);
            if(res.status==200){
                console.log("sucessful")
                let auth = document.getElementById("authCheck");
                _jwt= res.headers.get("Authorization");
                auth.innerHTML=_jwt;
                window.location.href = "poker.html";

            }
            else{
                console.log("failed");
                console.log(res.status);
            }
        })
}