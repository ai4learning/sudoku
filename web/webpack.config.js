const apiMocker = require('webpack-api-mocker')
const path = require('path')

module.exports = {
  mode: 'development',
  entry: path.resolve(__dirname, 'src/index.js'),
  output: {
    filename: 'bundle.js',
    path: path.resolve(__dirname, 'dist')
  },
  devServer: {
    // before(app) {
    //   apiMocker(app, path.resolve('./mock/index.js'))
    // }
    proxy: {
      '/api': {
        target: 'http://www.wordtraining.cn',
        changeOrigin: true,
        bypass: function(req, res, proxyOptions) {
          req.headers['Cookie'] = 'WordTrainingID=3BACC9D2D909189AB2FC75894D34612D32780A9002AB71E8366AE1E78E583B35DB9AC6FCB1DA852636420DD5A24747E51EFEE3EF65DD51072F362B5603FD0784D320273A302229EA06102DAA44B942B8A423EC416892623793194F61D26E5A3B; WordTrainingCode=4ACBA830566445D69209D94B01AAE12D88F54CD8428EC5942F80F5A7AB691A6BA5AF0931568A2FFC7CB615A35F89A39AD81889F2891C0B562DC72621FCAF78C224A3EB697CC2BBEC2831F31B86625DC9EEAEEE797E9FFFD44610C84E2CE2A064B84DEC15D0162A49204424DDA0464ACBA512D5FB9EE4F313E169983A11355CE9E87EBDA01A01C68E123F33379269B03F064CA554D411A219DDBE4D9E2616AA221AAAC5960E7F7AF504811C93CAE4C0CD31A78405FB98DD3C41E1833DCFAAE186843271487D758D0934E7F097694AD833400DF96523487103FC3C24E7B1EF6AC8'
        }
      }
    }
  }
}