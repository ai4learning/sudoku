var axios = require('axios')
var path = require('path')
var fs = require('fs')
var request = require('request')

for (var i = 1; i <= 17; i++) {
  axios.request({
    url: 'http://www.wordtraining.cn/api/Ajax/AjaxGetUnit',
    method: 'POST',
    data: {
      unit: i,
      moduleCode: '8a108cb7-42ad-314f-0142-ad33a0a70001'
    },
    headers: {
      'accept': 'application/json',
      'Cookie': 'WordTrainingID=3BACC9D2D909189AB2FC75894D34612D32780A9002AB71E8366AE1E78E583B35DB9AC6FCB1DA852636420DD5A24747E51EFEE3EF65DD51072F362B5603FD0784D320273A302229EA06102DAA44B942B8A423EC416892623793194F61D26E5A3B; WordTrainingCode=4ACBA830566445D69209D94B01AAE12D88F54CD8428EC5942F80F5A7AB691A6BA5AF0931568A2FFC7CB615A35F89A39AD81889F2891C0B562DC72621FCAF78C224A3EB697CC2BBEC2831F31B86625DC9EEAEEE797E9FFFD44610C84E2CE2A064B84DEC15D0162A49204424DDA0464ACBA512D5FB9EE4F313E169983A11355CE9E87EBDA01A01C68E123F33379269B03F064CA554D411A219DDBE4D9E2616AA221AAAC5960E7F7AF504811C93CAE4C0CD31A78405FB98DD3C41E1833DCFAAE186843271487D758D0934E7F097694AD833400DF96523487103FC3C24E7B1EF6AC8'
    }
  }).then(result => {
    (result.data.data || []).forEach(item => {
      var spelling = item.spelling
      var firstChar = spelling.charAt(0).toUpperCase()
      var filename = firstChar + '/' + spelling + '.mp3'
      var url = 'http://voice.wordtraining.cn/soundfile/' + filename
      console.log(url)
      if (!fs.existsSync(path.resolve('./', firstChar))) {
        fs.mkdirSync(path.resolve('./', firstChar))
      }
      filename = path.resolve('./', filename)
      var ws = fs.createWriteStream(filename)
      request({
        url: url,
        method: 'GET',
        headers: {
          'Cookie': 'WordTrainingID=3BACC9D2D909189AB2FC75894D34612D32780A9002AB71E8366AE1E78E583B35DB9AC6FCB1DA852636420DD5A24747E51EFEE3EF65DD51072F362B5603FD0784D320273A302229EA06102DAA44B942B8A423EC416892623793194F61D26E5A3B; WordTrainingCode=4ACBA830566445D69209D94B01AAE12D88F54CD8428EC5942F80F5A7AB691A6BA5AF0931568A2FFC7CB615A35F89A39AD81889F2891C0B562DC72621FCAF78C224A3EB697CC2BBEC2831F31B86625DC9EEAEEE797E9FFFD44610C84E2CE2A064B84DEC15D0162A49204424DDA0464ACBA512D5FB9EE4F313E169983A11355CE9E87EBDA01A01C68E123F33379269B03F064CA554D411A219DDBE4D9E2616AA221AAAC5960E7F7AF504811C93CAE4C0CD31A78405FB98DD3C41E1833DCFAAE186843271487D758D0934E7F097694AD833400DF96523487103FC3C24E7B1EF6AC8',
          'Referer': 'http://www.wordtraining.cn/'
        }
      }).pipe(ws)
    })
  })
}

// var axios = require('axios')
// var path = require('path')
// var fs = require('fs')
// var request = require('request')

// var Cookie = 'WordTrainingID=7AE5AF78B4E0E1884FE6F5DCFB13F65FB88D50C457992641AD3C82D7DC489F3F7D84F448D8F9FD395D9DBBE79DFEEB99B7BF92C5198D0DE4A4BE01C865F88163304177251DCDDE888328E06758F60408E39F6C1F495EC07CBD092AD67BDC37AA; WordTrainingCode=988079690DED31392E76451C613ED86A3C4D30AD4AE9775511DEED54EA5BA225811CC64882EE54BA0DB9E2E0382EB039664D66072050D66D3E82362AFEE33DE7B2A654847BE1D8A650824A366A7C7D09214B428887AF955AA37670DB5F38BF211DEEE8D23B4A9EC604DE160566D24B6840B292F1E4F84153CE84566A97C27E8003CA54B80E606279A70BB9C83782101F3D84D4827448A62392FCC5EBE286712E8F4FB997E469661C752C52307B8403E6E8453FBB6B2456A1329591B07DAF83ADB748E000C5B8426BD35D7D5575988008EEAE665818DEC93B2B3B666A7317BA24'

// axios.request({
//   url: 'http://www.wordtraining.cn/api/Ajax/AjaxGetUserBook',
//   method: 'POST',
//   headers: {
//     accept: 'application/json',
//     cookie: Cookie
//   }
// }).then(result => {
//   var books = result.data.books || []
//   for (var i = 0; i < 1; i++) {
//     var book = books[i]
//     for (var j = 1; j < 2; j++) {
//       ; ((book, unit) => axios.request({
//         url: 'http://www.wordtraining.cn/api/Ajax/AjaxGetUnit',
//         method: 'POST',
//         data: {
//           unit: j,
//           moduleCode: book.moduleCode
//         },
//         headers: {
//           accept: 'application/json',
//           cookie: Cookie
//         }
//       }).then(result => {
//         if (!fs.existsSync(path.resolve('./', 'soundfile'))) {
//           fs.mkdirSync(path.resolve('./', 'soundfile'))
//         }
//         ; (result.data.data || []).forEach(item => {
//           var spelling = item.spelling
//           var firstChar = spelling.charAt(0).toUpperCase()
//           var filename = firstChar + '/' + spelling + '.mp3'
//           console.log(filename)
//           var url = 'http://voice.wordtraining.cn/soundfile/' + filename
//           console.log(url)
//           if (!fs.existsSync(path.resolve('./soundfile', firstChar))) {
//             fs.mkdirSync(path.resolve('./soundfile', firstChar))
//           }
//           filename = path.resolve('./soundfile', filename)
//           var ws = fs.createWriteStream(filename)
//           request({
//             url: url,
//             method: 'GET',
//             headers: {
//               'Cookie': Cookie,
//               'Referer': 'http://www.wordtraining.cn/'
//             }
//           }).pipe(ws)
//         })
//       }))(book, j)
//     }
//   }
// })