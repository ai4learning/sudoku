var axios = require('axios')
var path = require('path')
var fs = require('fs')
var request = require('request')
var Bagpipe = require('bagpipe')

var bagpipe = new Bagpipe(30)

var Cookie = 'WordTrainingID=7AE5AF78B4E0E1884FE6F5DCFB13F65FB88D50C457992641AD3C82D7DC489F3F7D84F448D8F9FD395D9DBBE79DFEEB99B7BF92C5198D0DE4A4BE01C865F88163304177251DCDDE888328E06758F60408E39F6C1F495EC07CBD092AD67BDC37AA; WordTrainingCode=988079690DED31392E76451C613ED86A3C4D30AD4AE9775511DEED54EA5BA225811CC64882EE54BA0DB9E2E0382EB039664D66072050D66D3E82362AFEE33DE7B2A654847BE1D8A650824A366A7C7D09214B428887AF955AA37670DB5F38BF211DEEE8D23B4A9EC604DE160566D24B6840B292F1E4F84153CE84566A97C27E8003CA54B80E606279A70BB9C83782101F3D84D4827448A62392FCC5EBE286712E8F4FB997E469661C752C52307B8403E6E8453FBB6B2456A1329591B07DAF83ADB748E000C5B8426BD35D7D5575988008EEAE665818DEC93B2B3B666A7317BA24'

var imgs = [
  'content/image/listenagin.png',
  'content/image/playR_n.png',
  'content/image/mic_aminate.gif',
  'content/img/volume-medium.png',
  'content/img/spell-check.png',
  'content/image/study_collect.png',
  'content/image/word_spellright.png',
  'content/image/word_spellwrong.png',
  'content/img/right3.png',
  'content/img/wrong3.png',
  'content/image/dot_light.png',
  'content/image/dot.png',
  'content/image/coins0.png',
  'content/image/clock.png',
  'content/img/new_exp_global_bg.png',
  'content/image/time_5.png',
  'content/image/music_alpha.png',
  'content/image/study_close_nor.png',
  'content/image/btn_next1.png',
  'content/image/studywordicon.png',
  'content/img/wrong3.png',
  'content/image/word_spellwrong.png',
  'content/image/study_collect_over.png',
  'content/image/listenagino.png',
  'content/image/listenagin_pre.png',
  'content/image/btn_next.png',
  'content/image/mic.png'
].forEach(item => {
  bagpipe.push(downloadAndSave, 'http://www.wordtraining.cn/'+item, path.resolve('../web/', item), () => {})
})

function downloadAndSave(url, filename, cb) {
  console.log(filename)
  var ws = fs.createWriteStream(filename)
  request({
    url: url,
    method: 'GET',
    hostname: '119.23.152.14',
    headers: {
      'Cookie': Cookie,
      'Referer': 'http://www.wordtraining.cn/'
    }
  }).on('error', (err) => {
    console.log(err, url)
  }).pipe(ws).on('error', (err) => {
    console.error(err)
  }).on('finish', () => {
    console.log(url + ' : finished!')
    ws.end()
    setTimeout(() => {
      cb()
    }, 10)
  })
}