var origCanvas = document.querySelector("#original");
var origCtx = origCanvas.getContext("2d");

var origImg = new Image();
origImg.addEventListener('load', () => {
    origCtx.drawImage(origImg, 0, 0);
}, false);
origImg.src = "C:\\Users\\avtea\\Desktop\\repo\\webimp\\src\\main\\resources\\static\\imgInput\\g.png"

var canvas = document.querySelector("#reapply");
var ctx = canvas.getContext("2d");

var img = new Image();
img.addEventListener('load', () => {
    ctx.drawImage(img, 0, 0);
}, false);
img.src = "C:\\Users\\avtea\\Desktop\\repo\\webimp\\src\\main\\resources\\static\\imgOutput\\img0.png"

let userAction = false;
let x = 0;
let y = 0;

canvas.addEventListener("mousedown", (e) => {
    userAction = true;
    [x, y] = [e.offsetX, e.offsetY];
});

canvas.addEventListener('mouseup', () => userAction = false);
canvas.addEventListener("mouseout", () => userAction = false);

function reapply(e){
    if(!userAction) return;
    [x, y] = [e.offsetX, e.offsetY];

    var imgData = origCtx.getImageData(x, y, 1, 1);
    ctx.putImageData(x, y, x, y, 1, 1);
}

canvas.addEventListener('mousemove', reapply);