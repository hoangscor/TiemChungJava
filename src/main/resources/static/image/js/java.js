const addressbtn = document.querySelector('#address-form')
const addressclose = document.querySelector('#address-close')
//console.log(addressbtn)
addressbtn.addEventListener("click", function(){
    document.querySelector('.address-form') .style.display="flex"
})
addressclose.addEventListener("click", function(){
    document.querySelector('.address-form') .style.display="none"
})
/*------slider transition----------------------------------------------------------------------------------------------------------*/
const rightbtn = document.querySelector('.fa-chevron-right')
const leftbtn = document.querySelector('.fa-chevron-left')
const imgNumber = document.querySelectorAll('.slider-content-left-top img')
console.log(imgNumber.length)
let index = 0

rightbtn.addEventListener("click",function(){
    index = index + 1
    if(index>imgNumber.length-1){
        index=0
    }
    document.querySelector(".slider-content-left-top").style.right =index *100+"%"
})
leftbtn.addEventListener("click",function(){
    index = index - 1
    if(index<=0){
        index=imgNumber.length-1
    }
    document.querySelector(".slider-content-left-top").style.right =index *100+"%"
})
/*--------slider click--------------------------------------------------------------------------------------------------------------*/
const imgNumberLi = document.querySelectorAll('.slider-content-left-bottom li')
imgNumberLi.forEach(function(image,index){
    image.addEventListener('click',function(){
        removeactive()
        document.querySelector(".slider-content-left-top").style.right =index *100+"%"
        image.classList.add('active')
    })
})
function removeactive(){
    let imgactive = document.querySelector('.active')
    imgactive.classList.remove("active")

}
/*---------------Slider-transition-auto--------------------------------------------------------------------------------------------*/
function imgAuto(){
    index = index + 1 
    if(index>imgNumber.length-1){
        index=0
    }
    removeactive()
    document.querySelector(".slider-content-left-top").style.right =index *100+"%"
    imgNumberLi[index].classList.add('active')
   //console.log(index)
}
setInterval(imgAuto,3000)
/*---------------Slider-product----------------------------------------------*/
const rightbtntwo = document.querySelector('.fa-chevron-right-two')
const leftbtntwo = document.querySelector('.fa-chevron-left-two')
const imgNumbertwo = document.querySelectorAll('.slider-product-one-content-items')


rightbtntwo.addEventListener("click",function(){
    index = index + 1
    if(index>imgNumbertwo.length-1){
        index=0
    }
    document.querySelector(".slider-product-one-content-items-content").style.right =index *100+"%"
})
leftbtntwo.addEventListener("click",function(){
    index = index - 1
    if(index<=0){
        index=imgNumbertwo.length-1
    }
    document.querySelector(".slider-product-one-content-items-content").style.right =index *100+"%"
})


