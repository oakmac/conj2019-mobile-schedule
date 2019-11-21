function keyupSearchBarDelayed (evt) {
  var searchValue = $('#searchBar').val()
  console.log(searchValue)
}

function keyupSearchBar (evt) {
  setTimeout(keyupSearchBarDelayed, 1)
}

function addEvents () {
  $('#searchBar').on('keyup', keyupSearchBar)
}

function init () {
  addEvents()
}

init()
