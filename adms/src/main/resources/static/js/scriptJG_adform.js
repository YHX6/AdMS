//ajax here
let typeChooser = document.getElementById("ad-form-type");
let stationChooser = document.getElementById("ad-form-station");

window.onload =function (){
    $("#ajax-station-block").hide();
    $("#ajax-spotnumber-block").hide();
    $("#ajax-train-block").hide();
};


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
        displayStation();
        cleanTrain();
    }else if(typeChooser.value === "In_train"){
        $("#ajax-station-block").hide();
        $("#ajax-spotnumber-block").hide();
        $("#ajax-train-block").show();
        displayTrain();
        cleanStationSpot();
    }
})

stationChooser.addEventListener("change", function (){
    displaySpot();
})

function displayStation(){
    console.log(111);
    $.get("ajax/stations", function (response){
        console.log(response);
        let inner = "<option value=''></option>";
        for(let i in response){
            inner = inner + "<option value='"+ response[i] +"'>" + response[i]  +"</option>";
        }
        document.getElementById("ad-form-station").innerHTML = inner;
    })
}

function displaySpot(){
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
    $.get("ajax/trains", function (response){
        let inner = "<option value=''></option>";
        for(let i in response){
            inner = inner + "<option value='"+ response[i] +"'>" + response[i]  +"</option>";
        }
        document.getElementById("ad-form-train").innerHTML = inner;
    })
}

function cleanStationSpot(){
    document.getElementById("ad-form-station").innerHTML = "<option value=''></option>";
    document.getElementById("ad-form-spotNumber").innerHTML = "<option value=''></option>";
}

function cleanTrain(){
    document.getElementById("ad-form-train").innerHTML ="<option value=''></option>";
}


//form validation
(function validateForm(){
    let form = document.querySelector(".form-valide");

    form.addEventListener("submit", (e) => {
        let isValid = true;


        if(document.getElementById("ad-form-type").value === ""){
            isValid = false;
            alert("Please select type");
            e.preventDefault();
            return;
        }

        if(document.getElementById("ad-form-spotNumber").value === "" &&
            document.getElementById("ad-form-train").value === ""){
            isValid = false;
            alert("Please select spot_number！");
            e.preventDefault();
            return;
        }

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

        // if(!isValid){
        //     e.preventDefault();
        // }

    })

})();