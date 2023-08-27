document.getElementById("reset-form").addEventListener("submit", (e) => {
    let invalid = false;


    document.querySelector(".warning-pass-old").innerHTML = "";
    if(!document.getElementById("password-old").value){
        document.querySelector(".warning-pass-old").innerHTML = "  请输入原始密码！"
        invalid = true;
    }

    // let passNewWarning = document.querySelector(".warning-pass-new").innerHTML;
    let passNew = document.getElementById("password-new").value;
    document.querySelector(".warning-pass-new").innerHTML = "";
    if(!passNew){
        document.querySelector(".warning-pass-new").innerHTML = "  请输入新密码！"
        invalid = true;
    }else if(passNew.length < 6 || passNew.length > 20){
        document.querySelector(".warning-pass-new").innerHTML = "  密码长度应为6-20字符！"
        invalid = true;
    }

    // let passConfirmWarning = document.querySelector(".warning-pass-confirm").innerHTML;
    let passConfirm = document.getElementById("password-confirm").value;
    document.querySelector(".warning-pass-confirm").innerHTML = document.querySelector(".warning-pass-confirm").innerHTML = "";
    if(!passConfirm || passConfirm !== passNew){
        document.querySelector(".warning-pass-confirm").innerHTML = "  密码不一致！"
        invalid = true;
    }

    if(invalid){
        e.preventDefault();
    }
})