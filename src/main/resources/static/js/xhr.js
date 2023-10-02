const requestURL = 'http://localhost:8080/30'

function sendRequest(method, url, body= null) {
    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest()


        xhr.open(method, url, true)        // xhr object bilen taze soyedinenya
        xhr.responseType = "json"
        xhr.setRequestHeader('Content-Type', 'application/json')


        xhr.onload = () => {
            if (xhr.status >= 400) {
                reject(xhr.response)
            } else {
                resolve(xhr.response)
            }
        }
        xhr.onerror = () => {
            reject(xhr.response)
        }

        xhr.send(JSON.stringify(body))
    })
}

const body = {
    name : 'Annadyrdy',
    surname : 'Baymyradow'
}

sendRequest('DELETE', requestURL, body)
    .then(data => console.log(data))
    .catch(err => console.log(err))


// sendRequest('POST', requestURL, body)
//     .then(data => console.log(data))
//     .catch(err => console.log(err))

// sendRequest('GET', requestURL)
//     .then(data => console.log(data))
//     .catch(err => console.log(err))
