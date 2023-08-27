window.onload = function (){
    document.querySelector(".add-train-block").style.display = "none";
    document.querySelector("#spot-add-train").value = "/";

    document.querySelector(".spot-update-form").style.display = "none";
    document.querySelector(".spot-update-title").style.display = "none";

    document.querySelector(".spot-switch").style.display = "none";

    displayStation();
    displaySpot();
    displayTrain();
}


$('#spot-add-type').change(function(){
    if($(this).val() === "站内"){
        $(".add-station-block").show();
        $("#spot-add-station").val("");


        $(".add-spotNumber-block").show();
        $("#spot-add-spotNumber").val("");


        $(".add-train-block").hide();
        $("#spot-add-train").val("/");
    }else{
        $(".add-station-block").hide();
        $("#spot-add-station").val("/");

        $(".add-spotNumber-block").hide();
        $("#spot-add-spotNumber").val("/");

        $(".add-train-block").show();
        $("#spot-add-train").val("");
    }
})


function selectUserForm(theId) {
    let selectedId = theId.substring(11);

    switchFormUpdate();


    const id = document.getElementById("id_" + selectedId).innerText;
    document.getElementById("spot-id").value = id;

    const type = document.getElementById("type_" + selectedId).innerText;
    document.getElementById("spot-update-type").value = type;

    const station = document.getElementById("station_" + selectedId).innerText;
    document.getElementById("spot-update-station").value = station;

    const spotnumber = document.getElementById("spotnumber_" + selectedId).innerText;
    document.getElementById("spot-update-spotNumber").value = spotnumber;


    const train = document.getElementById("train_" + selectedId).innerText;
    document.getElementById("spot-update-train").value = train;


    const status = document.getElementById("status_" + selectedId).innerText;
    document.getElementById("spot-update-status").value = status;

    const remark = document.getElementById("remark_" + selectedId).innerText;
    document.getElementById("spot-update-remark").value = remark;

    //alert(authority);
}

function switchFormUpdate(){
    document.querySelector(".spot-add-form").style.display = "none";
    document.querySelector(".spot-update-form").style.display = "block";

    document.querySelector(".spot-add-title").style.display = "none";
    document.querySelector(".spot-update-title").style.display = "block";

    document.querySelector(".spot-switch").style.display = "flex";
}

function switchFormAdd(){
    document.querySelector(".spot-add-form").style.display = "block";
    document.querySelector(".spot-update-form").style.display = "none";

    document.querySelector(".spot-add-title").style.display = "block";
    document.querySelector(".spot-update-title").style.display = "none";

    document.querySelector(".spot-switch").style.display = "none";
}

function deleteSpot(event) {
    return confirm("确定删除该广告点位？");
}

// add check for add form

(function () {
    const form = document.querySelector(".spot-add-form");

    form.addEventListener("submit", (e) => {
        let invalid = false;

        const station = document.getElementById("spot-add-station").value;
        const stationwarning = document.querySelector(".warning-add-station");
        stationwarning.innerHTML = "";
        if (!station) {
            stationwarning.innerHTML = "车站号为空！";
            invalid = true;
        } else if (station.length > 20) {
            stationwarning.innerHTML = "车站号长度应小于20！！";
            invalid = true;
        }

        const spotNumber = document.getElementById("spot-add-spotNumber").value;
        const spotNumberwarning = document.querySelector(".warning-add-spotNumber");
        spotNumberwarning.innerHTML = "";
        if (!spotNumber) {
            spotNumberwarning.innerHTML = "点位编号为空！";
            invalid = true;
        } else if (spotNumber.length > 20) {
            spotNumberwarning.innerHTML = "点位编号长度应小于20！！";
            invalid = true;
        }

        const train = document.getElementById("spot-add-train").value;
        const trainwarning = document.querySelector(".warning-add-train");
        trainwarning.innerHTML = "";
        if (!train) {
            trainwarning.innerHTML = "列车号为空！";
            invalid = true;
        } else if (train.length > 20) {
            trainwarning.innerHTML = "列车号长度应小于20！！";
            invalid = true;
        }

        const status = document.getElementById("spot-add-status").value;
        const statuswarning = document.querySelector(".warning-add-status");
        statuswarning.innerHTML = "";
        if (status === "/") {
            statuswarning.innerHTML = "请选择占用状态！！";
            invalid = true;
        }

        const remark = document.getElementById("spot-add-remark").value;
        const remarkwarning = document.querySelector(".warning-add-remark");
        remarkwarning.innerHTML = "";
        if (remark.length > 255) {
            remarkwarning.innerHTML = "备注长度应小于255！";
            invalid = true;
        }



        if (invalid) {
            e.preventDefault();
        }else{
            alert("提交成功!");
        }

    });
})();


(function () {
    const form = document.querySelector(".spot-update-form");

    form.addEventListener("submit", (e) => {
        let invalid = false;

        const station = document.getElementById("spot-update-station").value;
        const stationwarning = document.querySelector(".warning-update-station");
        stationwarning.innerHTML = "";
        if (!station) {
            stationwarning.innerHTML = "车站号为空！";
            invalid = true;
        } else if (station.length > 20) {
            stationwarning.innerHTML = "车站号长度应小于20！！";
            invalid = true;
        }

        const spotNumber = document.getElementById("spot-update-spotNumber").value;
        const spotNumberwarning = document.querySelector(".warning-update-spotNumber");
        spotNumberwarning.innerHTML = "";
        if (!spotNumber) {
            spotNumberwarning.innerHTML = "点位编号为空！";
            invalid = true;
        } else if (spotNumber.length > 20) {
            spotNumberwarning.innerHTML = "点位编号长度应小于20！！";
            invalid = true;
        }

        const train = document.getElementById("spot-update-train").value;
        const trainwarning = document.querySelector(".warning-update-train");
        trainwarning.innerHTML = "";
        if (!train) {
            trainwarning.innerHTML = "列车号为空！";
            invalid = true;
        } else if (train.length > 20) {
            trainwarning.innerHTML = "列车号长度应小于20！！";
            invalid = true;
        }

        const status = document.getElementById("spot-update-status").value;
        const statuswarning = document.querySelector(".warning-update-status");
        statuswarning.innerHTML = "";
        if (status === "/") {
            statuswarning.innerHTML = "请选择占用状态！！";
            invalid = true;
        }

        const remark = document.getElementById("spot-update-remark").value;
        const remarkwarning = document.querySelector(".warning-update-remark");
        remarkwarning.innerHTML = "";
        if (remark.length > 255) {
            remarkwarning.innerHTML = "备注长度应小于255！";
            invalid = true;
        }



        if (invalid) {
            e.preventDefault();
        }else{
            alert("提交修改成功!");
        }

    });
})();


//ajax
function displayStation(){
    $.get("/ajax/stations-trains", function (response){
        let inner = "<option value=''></option>";
        for(let i in response){
            inner = inner + "<option value='"+ response[i] +"'>" + response[i]  +"</option>";
        }
        document.getElementById("search-bar-station").innerHTML = inner;
    })
}

function displaySpot(){
    $.get("/ajax/spotnumbers-nostation", function (response){
        // console.log(response);
        let inner = "<option value=''></option>";
        for(let i in response){
            inner = inner + "<option value='"+ response[i] +"'>" + response[i]  +"</option>";
        }
        document.getElementById("search-bar-spotnumber").innerHTML = inner;
    })
}

function displayTrain(){
    $.get("/ajax/trains-stations", function (response){
        // console.log(response);
        let inner = "<option value=''></option>";
        for(let i in response){
            inner = inner + "<option value='"+ response[i] +"'>" + response[i]  +"</option>";
        }
        document.getElementById("search-bar-train").innerHTML = inner;
    })
}

function displayTable(response){
    let body = document.getElementById("dashboard-table-body");
    let inner =  response["currentPage"] == 1 ? "" : body.innerHTML;
    let data = response["response"];


    for(let row in data){

        inner = inner +
            "<tr ><td id='id_" + data[row].id+ "'>" + data[row].id+ "</td>" +
            "<td id='type_" + data[row].id+ "'>" + data[row].type+ "</td>" +
            "<td id='station_" + data[row].id+ "'>" + data[row].station+ "</td>" +
            "<td id='spotnumber_" + data[row].id+ "'>" + data[row].spotNumber+ "</td>" +
            "<td id='train_" + data[row].id+ "'>" + data[row].train+ "</td>" +
            "<td id='status_" + data[row].id+ "'> " + data[row]["status"]+ "</td>" +
            "<td id='remark_" + data[row].id+ "'> " + data[row].remark+ "</td>" +
            "<td><div class=\"operation-btns\">" +
            "<span class=\"jsgrid-button jsgrid-edit-button ti-pencil\" type=\"button\" title=\"Edit\" id=\"update-btn_"+ data[row].id+"\" onclick=\"selectUserForm(this.id)\"></span>" +
            "<a  th:id=\"delete-btn_"+ data[row].id+"\" href=\"/spots/delete?id="+ data[row].id+"\" onclick=\"return deleteSpot(event)\"><span class=\"jsgrid-button jsgrid-delete-button ti-trash\" type=\"button\" title=\"Delete\"></span></a>\n" +
            "</div></td>"+
            "</tr>"
    }
    body.innerHTML = inner;
    let has = response["currentPage"]*10> response["totalItems"] ? response["totalItems"] : response["currentPage"]*10;
    if(has === response["totalItems"]){
        document.getElementById("page-block").innerHTML = "<div class='load-span'></div><div>记录数:"+  has + "/" + response["totalItems"] + "</div>";
    }else{
        document.getElementById("page-block").innerHTML = "<div class='load-span'><button class='load-btn' onclick='getAllByQuery("+ (response["currentPage"] + 1) +")'>加载记录...</button></div><div>记录数:"+  has + "/" + response["totalItems"] + "</div>";
    }

}




let idElement = document.getElementById("search-bar-id");
let typeElement = document.getElementById("search-bar-type");
let stationElement = document.getElementById("search-bar-station");
let spotnumberElement = document.getElementById("search-bar-spotnumber");
let trainElement = document.getElementById("search-bar-train");
let statusElement = document.getElementById("search-bar-status");



function getAllByQuery(pageNo){
    $.get("/spots-query/page/"+pageNo,{id:idElement.value, type:typeElement.value, station:stationElement.value, spotNumber:spotnumberElement.value,
        train:trainElement.value, status:statusElement.value}, function (response){
        console.log(response);
        displayTable(response)
    })
}

idElement.addEventListener("input", (e) =>{
    getAllByQuery(1);
})

typeElement.addEventListener("input", (e) =>{
    getAllByQuery(1);
})
stationElement.addEventListener("input", (e) =>{
    getAllByQuery(1);
})

spotnumberElement.addEventListener("input", (e) =>{
    getAllByQuery(1);
})
trainElement.addEventListener("input", (e) =>{
    getAllByQuery(1);
})
statusElement.addEventListener("input", (e) =>{
    getAllByQuery(1);
})

function cleanSearch(){
    idElement.value = "";
    typeElement.value = "";
    stationElement.value = "";
    spotnumberElement.value = "";
    trainElement.value = "";
    statusElement.value = "";

    getAllByQuery(1);
}
