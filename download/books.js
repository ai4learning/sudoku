var axios = require('axios')
var path = require('path')
var fs = require('fs')
var request = require('request')

var Cookie = 'WordTrainingID=7AE5AF78B4E0E1884FE6F5DCFB13F65FB88D50C457992641AD3C82D7DC489F3F7D84F448D8F9FD395D9DBBE79DFEEB99B7BF92C5198D0DE4A4BE01C865F88163304177251DCDDE888328E06758F60408E39F6C1F495EC07CBD092AD67BDC37AA; WordTrainingCode=988079690DED31392E76451C613ED86A3C4D30AD4AE9775511DEED54EA5BA225811CC64882EE54BA0DB9E2E0382EB039664D66072050D66D3E82362AFEE33DE7B2A654847BE1D8A650824A366A7C7D09214B428887AF955AA37670DB5F38BF211DEEE8D23B4A9EC604DE160566D24B6840B292F1E4F84153CE84566A97C27E8003CA54B80E606279A70BB9C83782101F3D84D4827448A62392FCC5EBE286712E8F4FB997E469661C752C52307B8403E6E8453FBB6B2456A1329591B07DAF83ADB748E000C5B8426BD35D7D5575988008EEAE665818DEC93B2B3B666A7317BA24'

axios.request({
  url: 'http://www.wordtraining.cn/api/Ajax/AjaxGetUserBook',
  method: 'POST',
  headers: {
    accept: 'application/json',
    cookie: Cookie
  }
}).then(result => {
  if (!fs.existsSync(path.resolve('./', 'books'))) {
    fs.mkdirSync(path.resolve('./', 'books'))
  }
  var books = result.data.books || []
  for(var i = 0; i < books.length; i++) {
    var book = books[i]
    if (!fs.existsSync(path.resolve('./books', book.bookName))) {
      fs.mkdirSync(path.resolve('./books', book.bookName))
    }
    for(var j = 1; j <= book.totalUnitNbr; j++) {
      ;((book, unit) => axios.request({
        url: 'http://www.wordtraining.cn/api/Ajax/AjaxGetUnit',
        method: 'POST',
        data: {
          unit: j,
          moduleCode: book.moduleCode
        },
        headers: {
          accept: 'application/json',
          cookie: Cookie
        }
      }).then(result => {
        fs.writeFile(path.resolve('./books', book.bookName, unit+'.json'), JSON.stringify(result.data, null, 2), () => {})
      }))(book, j)
    }
  }
})