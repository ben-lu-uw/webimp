var origCanvas = document.querySelector("#original");
var origCtx = origCanvas.getContext("2d");

var origImg = new Image();
origImg.addEventListener('load', () => {
    
    origCanvas.width = origImg.width;
    origCanvas.height = origImg.height;

    origCtx.drawImage(origImg, 0, 0);
}, false);

var request = new XMLHttpRequest();
let url = "/listfile";
request.open("GET", url, false);
request.send(null);
if (request.status == 200){
    let name = request.response;
    alert(name);
    origImg.src = name;
}else{
    alert(request.status + request.statusText);
}

var canvas = document.querySelector("#reapply");
var ctx = canvas.getContext("2d");

ctx.lineJoin = "round";
ctx.lineCap = "round";
ctx.lineWidth = 1;

var img = new Image();
img.addEventListener('load', () => {
    canvas.width = origImg.width;
    canvas.height = origImg.height;
    ctx.drawImage(img, 0, 0);
}, false);
img.src = "\\imgOutput\\img0.png";

let userAction = false;

//let pixelData = [];
let x = 0;
let y = 0;

function determineStrokeStyle(xIn, yIn){
    var imgData = origCtx.getImageData(xIn, yIn, 1, 1);
    var r = imgData.data[0];
    var g = imgData.data[1];
    var b = imgData.data[2];

    let rgb = `rgb(${r}, ${g}, ${b})`;
    ctx.strokeStyle = rgb;
}

canvas.addEventListener("mousedown", (e) => {
    userAction = true;
    [x, y] = [e.offsetX, e.offsetY];
    determineStrokeStyle(x, y);
    //pixelData.push({"x":x, "y":y, "r":r, "g":g, "b":b});

});

canvas.addEventListener('mouseup', () => userAction = false);
canvas.addEventListener("mouseout", () => userAction = false);

function reapply(e){
    if(!userAction) return;
    ctx.beginPath();
    ctx.moveTo(x, y);
    ctx.lineTo(e.offsetX, e.offsetY)
    ctx.stroke();
    [x, y] = [e.offsetX, e.offsetY];

    determineStrokeStyle(x, y);
    //pixelData.push({"x":x, "y":y, "r":r, "g":g, "b":b});
}

canvas.addEventListener('mousemove', reapply);

function download(event){
    if (event.type == "click"){
        var link = document.querySelector("#download");
        if (link.style.display === "none"){
            link.style.display = "block";
        }else{
            link.style.display = "none";
        }
        var dataURL = canvas.toDataURL("image/png");
        link.href = dataURL;    
    }
}

function reveal(){
    var link = document.querySelector("#download");
    if (link.style.display === "none"){
        link.style.display = "block";
    }else{
        link.style.display = "none";
    }
}

let downloadBtn = document.querySelector("#linkBtn");
downloadBtn.addEventListener("click", download);

/*
function submitChanges(event){
    if (event.type == "click"){
        var request = new XMLHttpRequest();
        let url = "/rsc";
        request.open("POST", url, true);
        request.setRequestHeader('Content-Type', 'application/json');
        request.send(JSON.stringify(pixelData));
    }
}

let submitBtn = document.querySelector("#submit");
submitBtn.addEventListener("click", submitChanges);
*/


