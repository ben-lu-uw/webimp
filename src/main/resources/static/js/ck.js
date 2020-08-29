let load = document.querySelector("#loadimg")
let img = document.querySelector("#img");

function handleLoad(event){
    if (event.type == "click"){
        var request = new XMLHttpRequest();
        let url = "/listfile";
        request.open("GET", url, false);
        request.send(null);
        if (request.status == 200){
            let name = request.response;
            alert(name);
            img.src = name;
        }else{
            alert(request.status + request.statusText);
        }
    }

}
load.addEventListener("click", handleLoad);

function eventHandler(event){
    if (event.type == "dblclick"){
        var xPos = event.offsetX;
        var yPos = event.offsetY;
        var request = new XMLHttpRequest();
        let url = "/ck?x="+ xPos +"&y=" + yPos;
        request.open("GET", url, false);
        request.send(null);
        if (request.status == 200){
            alert(request.responseText);
        }else{
            alert(request.status + request.statusText);
        }
    }
}

img.addEventListener('dblclick', eventHandler);
