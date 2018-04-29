var fs = require('fs')
var proxy = {}
var API_GET = 'GET /api/Ajax/', API_POST = 'POST /api/Ajax/'

var files = fs.readdirSync(__dirname)
files.forEach(file => {
  if(file === 'index.js') return
  var json = require('./' + file)
  var filename = file.replace(/\.json$/g, '')
  proxy[API_GET + filename] = json
  proxy[API_POST + filename] = json
})

module.exports = proxy