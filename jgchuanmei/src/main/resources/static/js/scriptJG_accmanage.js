window.onload = function (){
  ToggleUpdateInfo("none", "none");
  ToggleUpdatePass("none","none");
}

// toggle between forms
function ToggleAddForm(style){
  document.querySelector(".user-add-form").style.display = style;
  document.querySelector(".spot-add-title").style.display = style;
}

function ToggleUpdateInfo(style,  btn){
  document.querySelector(".user-update-info").style.display = style;
  document.querySelector(".spot-update-info").style.display = style;
  document.querySelector(".spot-switch-info").style.display = btn;
}

function ToggleUpdatePass(style,  btn){
  document.querySelector(".user-update-pass").style.display = style;
  document.querySelector(".spot-update-pass").style.display = style;
  document.querySelector(".spot-switch-pass").style.display = btn;

}

function switchForm(){
  ToggleAddForm("block")
  ToggleUpdateInfo("none", "none");
  ToggleUpdatePass("none","none");
}


// ajax
let usernameInput = document.getElementById("user-add-username");
function detectUsername(){
  $.get("/ajax/username-exist", {username:usernameInput.value},function (response){
    // console.log(response);
    if(response){
      document.querySelector(".warning-username-add").innerHTML = "用户名已存在！";
    }else{
      document.querySelector(".warning-username-add").innerHTML = "";
    }
  })
}

usernameInput.addEventListener("input", (e) => {

  detectUsername();
})



// fill update form
function selectUserForm(theId) {
  let selectedId = theId.substring(11);

  const authority = document.getElementById(
      "authority_" + selectedId
  ).innerText;
  if (authority === "员工") {
    document.getElementById("user-update-authority").value = "员工";
  } else if (authority === "管理员") {
    document.getElementById("user-update-authority").value = "管理员";
  } else {
    alert("您没有改权限！");
    return;
  }


  const id = document.getElementById("id_" + selectedId).innerText;
  document.getElementById("user-update-id").value = id;

  const name = document.getElementById("name_" + selectedId).innerText;
  document.getElementById("user-update-name").value = name;

  const username = document.getElementById("username_" + selectedId).innerText;
  document.getElementById("user-update-username").value = username;


  const phone = document.getElementById("phone_" + selectedId).innerText;
  document.getElementById("user-update-phone").value = phone;

  const isNonlocked = document.getElementById("accountNonLocked_" + selectedId).innerText;
  document.getElementById("user-update-locked").value = isNonlocked == "是" ? "false":"true";

  ToggleAddForm("none")
  ToggleUpdateInfo("block","flex");
  ToggleUpdatePass("none","none");
}

function selectUserPass(theId){
  let selectedId = theId.substring(10);

  const authority = document.getElementById(
      "authority_" + selectedId
  ).innerText;
  if (authority === "超级号") {
    alert("您没有改权限！");
    return;
  }
  const id = document.getElementById("id_" + selectedId).innerText;
  document.getElementById("user-pass-id").value = id;

  ToggleAddForm("none")
  ToggleUpdateInfo("none","none");
  ToggleUpdatePass("block","flex");
}

// add form validation
(function () {
  const form = document.querySelector(".user-add-form");

  form.addEventListener("submit", (e) => {
    let invalid = false;
    const id = document.getElementById("user-add-id").value;
    document.querySelector(".warning-id-add").innerHTML = "";
    if (!id) {
      document.querySelector(".warning-id-add").innerHTML = "员工ID为空！";
      invalid = true;
    } else if (id.length < 6 || id.length > 20) {
      document.querySelector(".warning-id-add").innerHTML =
          "员工ID应为长度6-20英文字符！";
      invalid = true;
    }

    const name = document.getElementById("user-add-name").value;
    document.querySelector(".warning-name-add").innerHTML = "";
    if (!name) {
      document.querySelector(".warning-name-add").innerHTML = "姓名为空！";
      invalid = true;
    } else if (name.length > 20) {
      document.querySelector(".warning-name-add").innerHTML = "姓名长度过长！";
      invalid = true;
    }

    const username = document.getElementById("user-add-username").value;
    if(document.querySelector(".warning-username-add").innerHTML === "用户名已存在！"){
      invalid = true;
    }else{
      document.querySelector(".warning-username-add").innerHTML = "";
      if (!username) {
        document.querySelector(".warning-username-add").innerHTML = "用户名为空！";
        invalid = true;
      } else if (username.length < 6 || username.length > 20) {
        document.querySelector(".warning-username-add").innerHTML =
            "用户名应为长度6-20英文字符！";
        invalid = true;
      }
    }





    const password = document.getElementById("user-add-password").value;
    document.querySelector(".warning-password-add").innerHTML = "";
    if (!password) {
      document.querySelector(".warning-password-add").innerHTML = "密码为空！";
      invalid = true;
    } else if (password.length < 6 || password.length > 20) {
      document.querySelector(".warning-password-add").innerHTML =
          "密码应为长度6-20英文字符！";
      invalid = true;
    }

    const confirm = document.getElementById("user-add-confirm-password").value;
    document.querySelector(".warning-confirm-add").innerHTML = "";
    if (!confirm || confirm !== password) {
      document.querySelector(".warning-confirm-add").innerHTML =
          "请确认数据密码一致！";
      invalid = true;
    }

    const phone = document.getElementById("user-add-phone").value;
    document.querySelector(".warning-phone-add").innerHTML = "";
    // console.log(phone);
    // console.log(!phone);
    // if(!phone && !containsOnlyNumbers(phone)){
    //     document.querySelector(".warning-phone-add").innerHTML = "手机号为无效！";
    //     invalid = true;
    // }
    // if (!phone) {
    //   document.querySelector(".warning-phone-add").innerHTML = "手机号为空！";
    //   invalid = true;
    // } else if (phone.length !== 11 || !containsOnlyNumbers(phone)) {
    //   document.querySelector(".warning-phone-add").innerHTML = "手机号为无效！";
    //   invalid = true;
    // }



    if (invalid) {
      e.preventDefault();
    }else{
      alert("创建成功!");
    }

  });
})();


//update info form validation
(function () {
  const form = document.querySelector(".user-update-info");

  form.addEventListener("submit", (e) => {
    let invalid = false;

    const name = document.getElementById("user-update-name").value;
    document.querySelector(".warning-name-info").innerHTML = "";
    if (!name) {
      document.querySelector(".warning-name-info").innerHTML = "姓名为空！";
      invalid = true;
    } else if (name.length > 20) {
      document.querySelector(".warning-name-info").innerHTML = "姓名长度过长！";
      invalid = true;
    }

    const username = document.getElementById("user-update-username").value;
    document.querySelector(".warning-username-info").innerHTML = "";
    if (!username) {
      document.querySelector(".warning-username-info").innerHTML = "用户名为空！";
      invalid = true;
    } else if (username.length < 6 || username.length > 20) {
      document.querySelector(".warning-username-info").innerHTML =
          "用户名应为长度6-20英文字符！";
      invalid = true;
    }


    const phone = document.getElementById("user-update-phone").value;
    document.querySelector(".warning-phone-info").innerHTML = "";
    // if(!phone && !containsOnlyNumbers(phone)){
    //   document.querySelector(".warning-phone-add").innerHTML = "手机号为无效！";
    //   invalid = true;
    // }
    // if (!phone) {
    //   document.querySelector(".warning-phone-info").innerHTML = "手机号为空！";
    //   invalid = true;
    // } else if (phone.length !== 11 || !containsOnlyNumbers(phone)) {
    //   document.querySelector(".warning-phone-info").innerHTML = "手机号为无效！";
    //   invalid = true;
    // }

    const auth = document.getElementById("user-update-authority").value;
    document.querySelector(".warning-auth-info").innerHTML = "";
    if(!auth){
      document.querySelector(".warning-auth-info").innerHTML = "请选择权限！";
      invalid = true;
    }


    if (invalid) {
      e.preventDefault();
    }else{
      alert("提交修改成功!");
    }

  });
})();

// update pass form validation
(function () {
  const form = document.querySelector(".user-update-pass");

  form.addEventListener("submit", (e) => {
    let invalid = false;

    const password = document.getElementById("user-pass-password").value;
    document.querySelector(".warning-password-pass").innerHTML = "";
    if (!password) {
      document.querySelector(".warning-password-pass").innerHTML = "密码为空！";
      invalid = true;
    } else if (password.length < 6 || password.length > 20) {
      document.querySelector(".warning-password-pass").innerHTML =
          "密码应为长度6-20英文字符！";
      invalid = true;
    }

    const confirm = document.getElementById("user-pass-confirm-password").value;
    document.querySelector(".warning-confirm-pass").innerHTML = "";
    if (!confirm || confirm !== password) {
      document.querySelector(".warning-confirm-pass").innerHTML =
          "请确认数据密码一致！";
      invalid = true;
    }

    if (invalid) {
      e.preventDefault();
    }else{
      alert("提交修改成功!");
    }

  });
})();


function containsOnlyNumbers(str) {
  return /^[0-9]+$/.test(str);
}

// delete user confirm
function deleteUser(theId) {
  const selectedId = theId.substring(11);
  const authority = document.getElementById(
      "authority_" + selectedId
  ).innerText;


  if (authority === "超级号") {
    alert("您没有改权限！");
    return false;
  } else {
    let b = confirm("确定删除改用户账号？");
    return b;
  }
}


