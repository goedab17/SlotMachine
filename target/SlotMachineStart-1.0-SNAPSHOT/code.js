var _jwt;
function login(name, pwd) {
    let user = {
        'user':name,
        'pwd':pwd
    };
    fetch("./api/login",{
        method:"POST",
        headers:{'ContentType': 'application/json'},
        body:JSON.stringify(user)
    })
        .then(res =>{
            if(res.status==200){
                return res.json();
                let auth = document.getElementById("authCheck");
                _jwt= res.headers.get("Authorization")
                auth.innerHTML=_jwt;
            }
        })
}