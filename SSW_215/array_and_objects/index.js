var computer = {
    model: "MacBook Pro (13-inch, 2017, Two Thunderbolt 3 ports)",
    memory: "8 GB 2133 MHz LPDDR3",
    CPU: "2.3 GHz Dual-Core Intel Core i5",
    GPU: "Intel Iris Plus Graphics 640 1536 MB",
    age: function() {
        var age = "2018"
        return age
    }
}

var array = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

function sqrtArray(x) {
    return Math.pow(x, 2);
}

console.log(array.map(sqrtArray))

var inventory = {
    storeName: "Bookstore",
    storeAdress: "1 Castle Point Terrace",
    stock: [
        { 
            title: 'title 1',
            author: 'author 1',
            price: 'price 1'
        },
        { 
            title: 'title 2',
            author: 'author 2',
            price: 'price 2'
        },
        { 
            title: 'title 3',
            author: 'author 3',
            price: 'price 3'
        },
        { 
            title: 'title 4',
            author: 'author 4',
            price: 'price 4'
        },
        { 
            title: 'title 5',
            author: 'author 5',
            price: 'price 5'
        }
    ]
}

console.log(inventory.stock[1].title)