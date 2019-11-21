if (!String.prototype.includes) {
  String.prototype.includes = function(search, start) {
    'use strict';

    if (search instanceof RegExp) {
      throw TypeError('first argument must not be a RegExp');
    }
    if (start === undefined) { start = 0; }
    return this.indexOf(search, start) !== -1;
  };
}

function keyupSearchBarDelayed (evt) {
  var lcSearchTxt = $('#searchBar').val().toLowerCase()
  var anyResults = false

  // show / hide rows
  $('tr.searchable').each(function (idx) {
    var $row = $(this)
    var rowSearchTxt = $row.data('searchtxt')
    if (lcSearchTxt === '' || rowSearchTxt.includes(lcSearchTxt)) {
      $row.show()
      anyResults = true
    } else {
      $row.hide()
    }
  })

  // show / hide day sections
  $('div.day-section').each(function () {
    var $section = $(this)
    var $rowsInsideSection = $section.find('tr.searchable')
    var hideSection = true
    $rowsInsideSection.each(function () {
      if (this.style.display !== 'none') hideSection = false
    })
    if (hideSection) {
      $section.hide()
    } else {
      $section.show()
    }
  })

  // show / hide no results message bar
  if (!anyResults) {
    $('#noSearchResultsMsg').show()
  } else {
    $('#noSearchResultsMsg').hide()
  }
}

function keyupSearchBar (evt) {
  setTimeout(keyupSearchBarDelayed, 1)
}

function clickHideBtn () {
  $('#showPastEventsBtn').show()
  $('#hidePastEventsBtn').hide()
}

function clickShowBtn () {
  $('#showPastEventsBtn').hide()
  $('#hidePastEventsBtn').show()
}

function addEvents () {
  $('#searchBar').on('keyup', keyupSearchBar)
  $('#hidePastEventsBtn').on('click', clickHideBtn)
  $('#showPastEventsBtn').on('click', clickShowBtn)
}

addEvents()
