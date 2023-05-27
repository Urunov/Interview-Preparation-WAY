class LibraryJSONAdapter implements LibraryAdapter {
    private library: LibraryJSON

    constructor(library: LibraryJSON){
        this.library = library
    }

    getBooks(){
        const books = this.library.getBooks()
        return JSON.parse(books) 
    }
}


const libraryJSON = new LibraryJSON();
const libraryJSONAdapter = new LibraryJSONAdapter(libraryJSON)
console.log(libraryJSONAdapter.getBooks())