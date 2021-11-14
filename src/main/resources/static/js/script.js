function showItemList(object){
    let parent = object.parentNode; 
    let list = parent.childNodes[3];
    let listElement = list.getElementsByTagName("LI");
   
    for(let e of listElement){
        if(e.style.display === "none"){
            e.style.display = "list-item";
        }
        else{
            e.style.display = "none";
        }
    }
}
