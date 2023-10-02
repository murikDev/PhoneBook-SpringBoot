// 1 -- peremennye --
var firstName = 'Myrat'
const lastName = 'Orjyyev'
let age = 23

console.log(firstName)
console.log(lastName)
console.log(age)

// 2 -- if else --

//const isProgrammer = true
//
//alert(isProgrammer)
//
//if (isProgrammer) {
//    alert('Myrat is Programmer')
//} else {
//    alert('Myrat isnt Programmer')
//}

//function method () {
//    let num = prompt('birinji sany giriz:')
//    let num2 = prompt('ikinji sany giriz:')
//    const oper = prompt('+,-,*,/  operasiyany saylan')
//    let result
//
//    if (oper === '+') {
//        result = num + num2
//        console.log(typeof(num))
//        console.log(typeof(num2))
//        console.log(result)
//    } else if (oper === '-') {
//        result = num - num2
//    } else if (oper === '*') {
//        result = num * num2
//    } else {
//         result = num / num2
//     }
//
//    console.log('netije: ' + result)
//}
//method()

const element = document.getElementById('hello')
const element1 = document.getElementById('hello1')
const element2 = document.getElementById('hello2')

console.log(element.textContent)


setTimeout( () => {
    addStylesTo(element, 'Murik')
}, 1500 )

setTimeout( () => {
    addStylesTo(element1, 'Say')
}, 3000 )

setTimeout( () => {
    addStylesTo(element2, 'Hello World')
}, 4500 )

function addStylesTo(node, text) {
    node.textContent = text
    node.style.color = 'red'
    node.style.background = 'black'
    node.style.textAlign = 'center'
    node.style.padding = '2rem'
}
