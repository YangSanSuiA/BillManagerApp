function load_provider() {
    $.ajax({
        url:"/providers",
        dataType:"json",
        async:true,
        type:"GET", //请求方式
        success:function(req){
            for(var i=0;i<req.providerList.length;i++){
                $(".providerList").html($(".providerList").html()+"<option value='"+req.providerList[i].pid+"'>"+req.providerList[i].providerName+"</option>");
            }
        },
        error:function(err){
            console.log("订单信息加载供应商出错:"+err)
        }
    });
}

function load_provider2(pid,pay) {

    $.ajax({
        url:"/providers",
        dataType:"json",
        async:true,
        type:"GET", //请求方式
        success:function(req){
            for(var i=0;i<req.providerList.length;i++){
                if(pid==req.providerList[i].pid){
                    $(".providerList").html($(".providerList").html()+"<option selected='selected' value='"+req.providerList[i].pid+"'>"+req.providerList[i].providerName+"</option>");
                    continue;
                }
                $(".providerList").html($(".providerList").html()+"<option value='"+req.providerList[i].pid+"'>"+req.providerList[i].providerName+"</option>");
            }
        },
        error:function(err){
            console.log("订单信息加载供应商出错:"+err);
        }
    });
    var str=document.getElementsByClassName("pay");
    if(pay==0){
        str[0].setAttribute("checked","checked");
    }else{
        str[1].setAttribute("checked","checked");
    }
}