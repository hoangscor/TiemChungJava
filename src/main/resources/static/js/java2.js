const btn = document.querySelectorAll('button')
btn.forEach(function(button,index){
button.addEventListener('click',function(event){{
    var btnItem = event.target
    var product = btnItem.parentElement
    var productImg = product.querySelector('img').src 
    var productName = product.querySelector('H1').innerText
    var productPrice = product.querySelector('span').innerText
    addcart(productPrice,productName,productImg)
}})
})
function addcart(productPrice,productName,productImg){
    var addtr = document.createElement('tr')

    var cartItem = document.querySelectorAll("tbody tr")
    for (var i = 0; i<cartItem.length; i++){
        var productT = document.querySelectorAll('.title')
            if(productT[i].innerHTML == productName ){
                 alert('Sản phẩm đã có trong cửa hàng')
                return
        }
    }
    var trcontent = '<tr><td style="display: flex;align-content: center;"><img style="width:70px ;" src="'+productImg+'" alt=""><span class="title">'+productName+'</span></td><td><p><span class="prices" >'+productPrice+'</span><sub>Đ</sub></p><td><input style="width: 30px; outline: none;" type="number"value="1"min="0"></td> <td style="cursor: pointer;"><span class="cart-delete">Xóa</span></td></tr>'
    addtr.innerHTML = trcontent
    var cartTable = document.querySelector('tbody')
    cartTable.append(addtr)
    carttotal()
    deleteCart()
    inputChange()
}
/*-------------------------Total price----------------------------------------------*/
function carttotal (){
    var cartItem = document.querySelectorAll("tbody tr")
    var totalC = 0
    for (var i = 0; i<cartItem.length; i++){
        var inputValue = cartItem[i].querySelector("input").value
        var productPrice = cartItem[i].querySelector(".prices").innerHTML
        totalA = inputValue*productPrice*1000
      //totalB = totalA.toLocaleString("de-DE")
        totalC = totalC + totalA
       //console.log(totalC)
       
    }
    var cartTotalA = document.querySelector(".price-total span")
    var cartPrice = document.querySelector('.cart-icon span')
    cartTotalA.innerHTML = totalC.toLocaleString('de-DE')
    cartPrice.innerHTML = totalC.toLocaleString('de-DE')
    inputChange()
}
/*-------------------------Deleta product----------------------------------------------*/
function deleteCart(){
    var cartItem = document.querySelectorAll("tbody tr")
    for (var i = 0; i<cartItem.length; i++){
        var productT = document.querySelectorAll(".cart-delete")
        productT[i].addEventListener("click",function(event){
            var cartDelete = event.target
            var cartitemR = cartDelete.parentElement.parentElement
            cartitemR.remove()
            carttotal()
        })
    }
}
/*-------------------------Cart----------------------------------------------*/

function inputChange(){
    var cartItem = document.querySelectorAll("tbody tr")
    for (var i = 0; i<cartItem.length; i++){
        var inputValue = cartItem[i].querySelector("input")
        inputValue.addEventListener("change",function(){
            carttotal()
        })
    }
}
const cartbtn = document.querySelector(".fa-square-xmark")
const cartshow = document.querySelector(".fa-cart-shopping")
cartshow.addEventListener('click',function(){
    document.querySelector(".cart").style.right="0"
})
cartbtn.addEventListener('click',function(){
    document.querySelector(".cart").style.right="-100%"
})