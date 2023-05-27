class LibraryCSV {
    getBooks(){
        const books = [
            {title:`Pishirib qo'yibdimi kodingda`, author: 'Kimsanboy'},
            {title:`IT ga kirib adashibsan`, author: 'Adashboy'},
            {title:`Haliyam tushunmadingmi`, author: 'Nimajon'},
            {title:`Bug titkilab mazza qil`, author: 'Nomsiz Jigar'},
        ]

        return ('title, author\r\n' + books.map((book) => `${book.title}, ${book.author}`).join('\r\n'))
    }
}