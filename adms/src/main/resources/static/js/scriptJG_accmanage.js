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
      document.querySelector(".warning-username-add").innerHTML = "Username existed！";
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
  if (authority === "Staff") {
    document.getElementById("user-update-authority").value = "Staff";
  } else if (authority === "Manager") {
    document.getElementById("user-update-authority").value = "Manager";
  } else {
    alert("Invalid Action！");
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
  if (authority === "Admin") {
    alert("Invalid Action！");
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
      document.querySelector(".warning-id-add").innerHTML = "UserID is required！";
      invalid = true;
    } else if (id.length < 6 || id.length > 20) {
      document.querySelector(".warning-id-add").innerHTML =
          "UserID should be 6-20 characters！";
      invalid = true;
    }

    const name = document.getElementById("user-add-name").value;
    document.querySelector(".warning-name-add").innerHTML = "";
    if (!name) {
      document.querySelector(".warning-name-add").innerHTML = "Name is required！";
      invalid = true;
    } else if (name.length > 20) {
      document.querySelector(".warning-name-add").innerHTML = "Name should less than 20 character！";
      invalid = true;
    }

    const username = document.getElementById("user-add-username").value;
    if(document.querySelector(".warning-username-add").innerHTML === "Username existed！"){
      invalid = true;
    }else{
      document.querySelector(".warning-username-add").innerHTML = "";
      if (!username) {
        document.querySelector(".warning-username-add").innerHTML = "Username is required！";
        invalid = true;
      } else if (username.length < 6 || username.length > 20) {
        document.querySelector(".warning-username-add").innerHTML =
            "Username should be 6-20 characters！";
        invalid = true;
      }
    }





    const password = document.getElementById("user-add-password").value;
    document.querySelector(".warning-password-add").innerHTML = "";
    if (!password) {
      document.querySelector(".warning-password-add").innerHTML = "Password is required！";
      invalid = true;
    } else if (password.length < 6 || password.length > 20) {
      document.querySelector(".warning-password-add").innerHTML =
          "Password should be 6-20 characters！";
      invalid = true;
    }

    const confirm = document.getElementById("user-add-confirm-password").value;
    document.querySelector(".warning-confirm-add").innerHTML = "";
    if (!confirm || confirm !== password) {
      document.querySelector(".warning-confirm-add").innerHTML =
          "Password not the same！";
      invalid = true;
    }

    const phone = document.getElementById("user-add-phone").value;
    document.querySelector(".warning-phone-add").innerHTML = "";

    if (invalid) {
      e.preventDefault();
    }else{
      alert("Created!");
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
      document.querySelector(".warning-name-info").innerHTML = "Name is required！";
      invalid = true;
    } else if (name.length > 20) {
      document.querySelector(".warning-name-info").innerHTML = "Name should be less than 20 characters！";
      invalid = true;
    }

    const username = document.getElementById("user-update-username").value;
    document.querySelector(".warning-username-info").innerHTML = "";
    if (!username) {
      document.querySelector(".warning-username-info").innerHTML = "Username is required！";
      invalid = true;
    } else if (username.length < 6 || username.length > 20) {
      document.querySelector(".warning-username-info").innerHTML =
          "Username should be 6-20 characters！";
      invalid = true;
    }


    const phone = document.getElementById("user-update-phone").value;
    document.querySelector(".warning-phone-info").innerHTML = "";


    const auth = document.getElementById("user-update-authority").value;
    document.querySelector(".warning-auth-info").innerHTML = "";
    if(!auth){
      document.querySelector(".warning-auth-info").innerHTML = "Select authority！";
      invalid = true;
    }


    if (invalid) {
      e.preventDefault();
    }else{
      alert("Modified!");
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
      document.querySelector(".warning-password-pass").innerHTML = "Password is required！";
      invalid = true;
    } else if (password.length < 6 || password.length > 20) {
      document.querySelector(".warning-password-pass").innerHTML =
          "Password should be 6-20 characters！";
      invalid = true;
    }

    const confirm = document.getElementById("user-pass-confirm-password").value;
    document.querySelector(".warning-confirm-pass").innerHTML = "";
    if (!confirm || confirm !== password) {
      document.querySelector(".warning-confirm-pass").innerHTML =
          "Confirm password！";
      invalid = true;
    }

    if (invalid) {
      e.preventDefault();
    }else{
      alert("Modified!");
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


  if (authority === "Admin") {
    alert("Invalid Action！");
    return false;
  } else {
    let b = confirm("Are you sure to deleted this account？");
    return b;
  }
}


