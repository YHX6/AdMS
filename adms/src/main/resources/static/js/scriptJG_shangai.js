
window.addEventListener("load", (event) => {
    displayDropDown();
});

function displayTable(response){
    let body = document.getElementById("dashboard-table-body");
    let inner =  response["currentPage"] == 1 ? "" : body.innerHTML;
    let now = new Date();
    let today = now.setDate(now.getDate());
    // let weeklater = now.setDate(now.getDate() + 7);
    let data = response["response"]


    for(let row in data){
        let status = "Non_expired";
        if(Date.parse(data[row].ddl) <= new Date(today)) {
            status = "Expired";
        }
        if(data[row]["isdroped"]){
            status = "Dropped";
        }



        inner = inner +
            "<tr ><td >" + data[row].id+ "</td>" +
            "<td >" + data[row].spotType+ "</td>" +
            "<td >" + data[row].station+ "</td>" +
            "<td >" + data[row].spotNumber+ "</td>" +
            "<td >" + data[row].train+ "</td>" +
            "<td > " + data[row].adType+ "</td>" +
            "<td > " + data[row].industryType+ "</td>" +
            "<td > " + data[row]["content"]+ "</td>" +
            "<td > " + data[row].company+ "</td>" +
            "<td > " + data[row].ddl+ "</td>" +
            "<td > " + status + "</td>" +
            "<td ><a href='/img/" + data[row].picture  + "'  target=\"_blank\" rel=\"noopener noreferrer\"><i class='ti-arrow-top-right'></i></a></td>" +
            "<td><div class=\"operation-btns\">" +
                "<a class=\"update-btn btn\" id=\"update-btn_" + data[row].id+ "\" href=\"/ads-update?id=" + data[row].id+ "\">Modify</a>" +
                "<a class=\"drop-btn btn\" id=\"drop-btn_" + data[row].id+ "\" href=\"/ads-shangai/drop?id=" + data[row].id+ "\" onclick=\"confirmDrop(this.id)\">Drop</a>" +
            "</div></td>"+


            "</tr>"
    }
    body.innerHTML = inner;
    let has = response["currentPage"]*10> response["totalItems"] ? response["totalItems"] : response["currentPage"]*10;
    if(has === response["totalItems"]){
        document.getElementById("page-block").innerHTML = "<div class='load-span'></div><div>Total:"+  has + "/" + response["totalItems"] + "</div>";
    }else{
        document.getElementById("page-block").innerHTML = "<div class='load-span'><button class='load-btn' onclick='getAllByQuery("+ (response["currentPage"] + 1) +")'>Loading...</button></div><div>Total:"+  has + "/" + response["totalItems"] + "</div>";
    }
}


function confirmDrop(id){
    return confirm("Drop this record?");
}

// load dropdowns
function displayDropDown(){
    displayStation();
    displaySpot();
    displayTrain();
    displayAdType();
    displayIndustry();
}

function displayStation(){
    $.get("/ajax/stations", function (response){
        let inner = "<option value=''></option>";
        for(let i in response){
            inner = inner + "<option value='"+ response[i] +"'>" + response[i]  +"</option>";
        }
        document.getElementById("search-bar-station").innerHTML = inner;
    })
}

function displaySpot(){
    $.get("/ajax/spotnumbers-nostation", function (response){
        let inner = "<option value=''></option>";
        for(let i in response){
            inner = inner + "<option value='"+ response[i] +"'>" + response[i]  +"</option>";
        }
        document.getElementById("search-bar-spotnumber").innerHTML = inner;
    })

}

function displayTrain(){
    $.get("/ajax/trains", function (response){
        // console.log(response);
        let inner = "<option value=''></option>";
        for(let i in response){
            inner = inner + "<option value='"+ response[i] +"'>" + response[i]  +"</option>";
        }
        document.getElementById("search-bar-train").innerHTML = inner;
    })
}

function displayAdType(){
    $.get("/ajax/ad-types", function (response){
        // console.log(response);
        let inner = "<option value=''></option>";
        for(let i in response){
            inner = inner + "<option value='"+ response[i] +"'>" + response[i]  +"</option>";
        }
        document.getElementById("search-bar-adtype").innerHTML = inner;
    })
}

function displayIndustry(){
    $.get("/ajax/industry-type", function (response){
        // console.log(response);
        let inner = "<option value=''></option>";
        for(let i in response){
            inner = inner + "<option value='"+ response[i] +"'>" + response[i]  +"</option>";
        }
        document.getElementById("search-bar-industrytype").innerHTML = inner;
    })
}

function cleanSearch(){
    idElement.value = "";
    typeElement.value = "";
    stationElement.value = "";
    spotnumberElement.value = "";
    trainElement.value = "";
    adtypeElement.value = "";
    industrytypeElement.value = "";
    contentElement.value = "";
    companyElement.value = "";
    ddlElement.value = "";
    statusElement.value = "";

    getAllByQuery();
}



// ajax
let idElement = document.getElementById("search-bar-id");
let typeElement = document.getElementById("search-bar-type");
let stationElement = document.getElementById("search-bar-station");
let spotnumberElement = document.getElementById("search-bar-spotnumber");
let trainElement = document.getElementById("search-bar-train");
let adtypeElement = document.getElementById("search-bar-adtype");
let industrytypeElement = document.getElementById("search-bar-industrytype");
let contentElement = document.getElementById("search-bar-content");
let companyElement = document.getElementById("search-bar-company");
let ddlElement = document.getElementById("search-bar-ddl");
let statusElement = document.getElementById("search-bar-status");



function getAllByQuery(pageNo){
    $.get("/ads-query/page/" + pageNo,{id:idElement.value, type:typeElement.value, station:stationElement.value, spotNumber:spotnumberElement.value,
    train:trainElement.value, adType:adtypeElement.value,  industryType:industrytypeElement.value, content:contentElement.value,
    company:companyElement.value, ddl:ddlElement.value, status:statusElement.value}, function (response){
        // console.log(response);
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
adtypeElement.addEventListener("input", (e) =>{
    getAllByQuery(1);
})
industrytypeElement.addEventListener("input", (e) =>{
    getAllByQuery(1);
})
contentElement.addEventListener("input", (e) =>{
    getAllByQuery(1);
})
companyElement.addEventListener("input", (e) =>{
    getAllByQuery(1);
})

ddlElement.addEventListener("input", (e) =>{
    getAllByQuery(1);
})
statusElement.addEventListener("input", (e) =>{
    getAllByQuery(1);
})


