//ajax here
let typeChooser = document.getElementById("ad-form-type");
let stationChooser = document.getElementById("ad-form-station");

window.onload =function (){
    displayStation();
    displaySpot();
    displayTrain();
    setTimeout(loadPageInfo, 1000);
   $("#update-form-picture-block").hide();
};

function loadPageInfo(){
    let id = document.getElementById("ad-form-id").value;
    $.get("ajax/ad-id", {id:id}).done(function (ad){
        // console.log(ad)
        document.getElementById("ad-form-type").value = ad["spotType"];
        document.getElementById("ad-form-station").value = ad["station"];
        document.getElementById("ad-form-spotNumber").value = ad["spotNumber"];
        document.getElementById("ad-form-train").value = ad["train"];
        document.getElementById("ad-form-adtype").value = ad["adType"];
        document.getElementById("ad-form-industrytype").value = ad["industryType"];
        document.getElementById("ad-form-company").value = ad["company"];
        document.getElementById("ad-form-content").value = ad["content"];
        document.getElementById("ad-form-ddl").value = ad["ddl"];
        document.getElementById("ad-form-remark").value = ad["remark"];
        document.getElementById("ad-form-isdroped").value =  ad["isdroped"];

    });

}

document.getElementById("ad-form-check").addEventListener("change", function (){
    if(this.checked){
        $("#update-form-picture-block").show();
    }else{
        $("#update-form-picture-block").hide();
    }
})




typeChooser.addEventListener("change",function (){
    if(typeChooser.value === ""){
        $("#ajax-station-block").hide();
        $("#ajax-spotnumber-block").hide();
        $("#ajax-train-block").hide();
        cleanStationSpot();
        cleanTrain();
    }else if(typeChooser.value === "In_station"){
        $("#ajax-station-block").show();
        $("#ajax-spotnumber-block").show();
        $("#ajax-train-block").hide();
        cleanStationSpot();
        cleanTrain();
    }else if(typeChooser.value === "In_train"){
        $("#ajax-station-block").hide();
        $("#ajax-spotnumber-block").hide();
        $("#ajax-train-block").show();
        cleanStationSpot();
        cleanTrain();
    }
})

stationChooser.addEventListener("change", function (){
    displaySpotByStation();
})

function displayStation(){
    $.get("ajax/stations-trains", function (response){
        let inner = "<option value=''></option>";
        for(let i in response){
            inner = inner + "<option value='"+ response[i] +"'>" + response[i]  +"</option>";
        }
        document.getElementById("ad-form-station").innerHTML = inner;
    })
}

function displaySpot(){
    $.get("ajax/spotnumbers-nostation", function (response){
        // console.log(response);
        let inner = "<option value=''></option>";
        for(let i in response){
            inner = inner + "<option value='"+ response[i] +"'>" + response[i]  +"</option>";
        }
        document.getElementById("ad-form-spotNumber").innerHTML = inner;
    })
}


function displaySpotByStation(){
    let data = stationChooser.value;
    let inner = "<option value=''></option>";


    if(data == null || data === ""){
        document.getElementById("ad-form-spotNumber").innerHTML = inner;
    }else{
        $.get("ajax/spotnumbers",{station:data}, function (response){
            for(let i in response){
                inner = inner + "<option value='"+ response[i] +"'>" + response[i]  +"</option>";
            }
            document.getElementById("ad-form-spotNumber").innerHTML = inner;
        })
    }

}

function displayTrain(){
    $.get("ajax/trains-stations", function (response){
        // console.log(response);
        let inner = "<option value=''></option>";
        for(let i in response){
            inner = inner + "<option value='"+ response[i] +"'>" + response[i]  +"</option>";
        }
        document.getElementById("ad-form-train").innerHTML = inner;
    })
}

function cleanStationSpot(){
    document.getElementById("ad-form-station").value = "";
    document.getElementById("ad-form-spotNumber").innerHTML = "<option value=''></option>";
}

function cleanTrain(){
    document.getElementById("ad-form-train").value = "";
}


function isNotChoosen(data){
    return (!data) || (data === "/");
}

//form validation
(function validateForm(){
    let form = document.querySelector(".form-valide");

    form.addEventListener("submit", (e) => {
        let isValid = true;


        if(document.getElementById("ad-form-type").value === ""){
            isValid = false;
            alert("Select type！");
            e.preventDefault();
            return;
        }


        if( isNotChoosen(document.getElementById("ad-form-spotNumber").value) &&
             isNotChoosen(document.getElementById("ad-form-train").value)){
            isValid = false;
            alert("Select spot_number！");
            e.preventDefault();
            return;
        }
        console.log("submit")

        if(!document.getElementById("ad-form-adtype").value){
            isValid = false;
            alert("Enter ad type！");
            e.preventDefault();
            return;
        }

        if(!document.getElementById("ad-form-industrytype").value){
            isValid = false;
            alert("Enter industry！");
            e.preventDefault();
            return;
        }

        if(!document.getElementById("ad-form-content").value){
            isValid = false;
            alert("Enter content！");
            e.preventDefault();
            return;
        }

        if(!document.getElementById("ad-form-company").value){
            isValid = false;
            alert("Enter company！");
            e.preventDefault();
            return;
        }

        if(!document.getElementById("ad-form-ddl").value){
            isValid = false;
            alert("Select ddl！");
            e.preventDefault();
            return;
        }


        if(document.getElementById("ad-form-check").checked &&
        !document.getElementById("ad-form-picture").value ){
            isValid = false;
            alert("Upload your file！");
            e.preventDefault();
            return;
        }


        if(!document.getElementById("ad-form-isdroped").value){
            isValid = false;
            alert("Select is dropped！");
            e.preventDefault();
            return;
        }

    })

})();