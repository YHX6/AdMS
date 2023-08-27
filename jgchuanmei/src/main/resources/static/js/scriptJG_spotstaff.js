window.onload = function (){
    displayStation();
    displaySpot();
    displayTrain();
    displayChart();
}

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

function displayChart(){
    let canvas = document.getElementById("pieChart");
    let ctx = canvas.getContext('2d');
    let canvas2 = document.getElementById("barChart");
    let ctx2 = canvas2.getContext('2d');



    // Global Options:
    Chart.defaults.global.defaultFontColor = 'black';
    Chart.defaults.global.defaultFontSize = 16;


    $.get("/charts/pie", function (response){
        // console.log(response);
        let data = {
            labels: ["已占用", "未占用","其他"],
            datasets: [
                {
                    fill: true,
                    backgroundColor: [
                        'rgb(255, 99, 132)',
                        'rgb(54, 162, 235)',
                        'rgb(255, 205, 86)'
                    ],
                    data: response,
                    hoverOffset: 4
                }
            ]
        };


        // Chart declaration:
        var myPieChart = new Chart(ctx, {
            type: 'pie',
            data: data,
            options: {
                responsive: true,
                rotation: -0.7 * Math.PI,
                title: {
                    display: true,
                    text: '点位占用情况图(%)'
                }
            }

        });
    })



// chart2
    $.get("/charts/bar", function (response){
        // console.log(response);
        let data2 = {
            labels: response[0],
            datasets: [
                {
                    label: '各车站正在上刊的广告数',
                    data: response[1],
                    backgroundColor: 'rgba(255, 99, 132, 0.5)',
                    borderColor:
                        'rgba(255, 99, 132)',
                    borderWidth: 1
                },
                {
                    label: '各车站广告位总数',
                    data: response[2],
                    backgroundColor:   'rgba(54, 162, 235, 0.5)',
                    borderColor:

                        'rgb(54, 162, 235)',
                    borderWidth: 1
                }


            ]
        };

        var myBarChart = new Chart(ctx2, {
            type: 'bar',
            data: data2,
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true
                    }
                },
                title: {
                    display: true,
                    text: '各站点销售情况'
                }
            }
        });
    })


}