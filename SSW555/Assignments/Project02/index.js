/**
 * parses GEDCOM files
 */

const fs = require('fs') 

const tags = ['INDI', 'NAME', 'SEX', 'BIRT', 'DEAT', 'FAMC', 'FAMS', 'FAM', 'MARR', 'HUSB', 'WIFE', 'CHIL', 'DIV', 'DATE', 'HEAD', 'TRLR', 'NOTE']

class Record{
  constructor(level, tag, arg, id) {
    this.level = level
    this.tag = tag
    this.arg = arg
    this.id = id
    this.isValid = this.isTagValid(tag)
  }

  isTagValid(tag) {
    if (tags.includes(tag)) {
      return 'Y'
    } else {
      return 'N'
    }
  }
}

fs.readFile('SSW555/Assignments/Project01/Project01.ged', (err, data) => {
  if (err) {
    throw err
  }

  var rawRecords = data.toString().split('\r').map((record) => {
    return record.trim()
  })

  var parseRecords = data.toString().split('\r').map((line) => {
    const lineSplit = line.trim().split(' ')
    var record

    if (lineSplit[0] === "1" || lineSplit[0] === '2') {
      record = new Record(lineSplit[0], lineSplit[1], lineSplit.splice(2).join(' '), null)
    }
    
    if (lineSplit[0] === '0') {
      if (lineSplit[2] === 'INDI' || lineSplit[2] === 'FAM') {
        record = new Record('0-1', lineSplit[2], null, lineSplit[1])
      } else {
        record = new Record('0-2', lineSplit[1], lineSplit.splice(2).join(' '), null)
      }
    }

    return record
  })

  for (let i = 0; i < rawRecords.length; i++){
    const parseRecord = parseRecords[i]
    var output
    const input = `--> ${rawRecords[i]}`
    if (parseRecord) {
      if (parseRecord.level === '0-1') {
        output = `<-- ${parseRecord.level[0]}|${parseRecord.tag}|${parseRecord.isValid}|${parseRecord.id}`
      } else {
        output = `<-- ${parseRecord.level[0]}|${parseRecord.tag}|${parseRecord.isValid}|${parseRecord.arg}`
      } 

      console.log(input)
      console.log(output)
    }
  }
})
