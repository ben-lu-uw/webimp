let img = document.querySelector('#img');

function eventHandler(event){
    if (event.type == "dblclick"){
        var xPos = event.clientX;
        var yPos = event.clientY;
        var request = new XMLHttpRequest();
        let url = "/ck?x="+ xPos +"&y=" + yPos;
        request.open("GET", url, false);
        request.send(null);
        // console.log(xPos, yPos);
        if (request.status == 200){
            alert(request.responseText);
        }else{
            alert(request.status + request.statusText);
        }
    }
}

img.addEventListener('dblclick', eventHandler);
img.src = "/imgInput/g.png";