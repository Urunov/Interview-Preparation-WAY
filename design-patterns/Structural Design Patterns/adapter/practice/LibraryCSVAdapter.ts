class LibraryCSVAdapter implements LibraryAdapter {
    
    private library: LibraryCSV
    
    constructor(library: LibraryCSV){
        this.library = library
    }

    getBooks(){
        const books = this.library.getBooks();
        const booksArray = books.split('\r\n');
        const booksData = booksArray.slice(1)

        return booksData.map((book) => {
            const bookInfo = book.split(',');
            
            return {
                title: bookInfo[0],
                author: bookInfo[1],
            }
        })
    }
}

const library = new LibraryCSV();
const libraryAdapter = new LibraryCSVAdapter(library);
console.log(libraryAdapter.getBooks())