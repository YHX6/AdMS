// fetch("/ads-unsettled")
// .then((respon) => {
//     if(respon.ok){
//         return respon.json();
//     }else{
//         throw new Error("网络错误");
//     }
// })
//     .then((data) => {
//
//         // displayTable(data);
//     })
// .catch((error) => console.log("Fecth error:", error));

//
// function displayTable(data){
//     let body = document.getElementById("dashboard-table-body");
//     let inner = "";
//     let now = new Date();
//     let today = now.setDate(now.getDate());
//     let weeklater = now.setDate(now.getDate() + 7);
//
//
//     for(let row in data){
//         let status = "未过期";
//         if(Date.parse(data[row].ddl) <= new Date(today)){
//             inner = inner + "<tr class='red-table-row'>";
//             status = "已过期";
//         }else if(Date.parse(data[row].ddl) < new Date(weeklater)){
//             inner = inner + "<tr class='yellow-table-row'>";
//         } else{
//             inner = inner + "<tr >";
//         }
//
//
//         inner = inner +
//             " <td >" + data[row].id+ "</td>" +
//             "<td >" + data[row].spotType+ "</td>" +
//             "<td >" + data[row].station+ "</td>" +
//             "<td >" + data[row].spotNumber+ "</td>" +
//             "<td >" + data[row].train+ "</td>" +
//             "<td > " + data[row].adType+ "</td>" +
//             "<td > " + data[row].industryType+ "</td>" +
//             "<td > " + data[row]["content"]+ "</td>" +
//             "<td > " + data[row].company+ "</td>" +
//             "<td > " + data[row].ddl+ "</td>" +
//             "<td > " + data[row].picture+ "<i class='ti-arrow-top-right'></i></td>" +
//             "<td > " + data[row].remark+ "</td>" +
//             "<td > " + status + "</td>" +
//             "</tr>"
//     }
//     body.innerHTML = inner;
// }
//
