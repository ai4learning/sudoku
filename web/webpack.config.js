const path = require('path')

var content_paths = []
;([
  'image',
  'img',
  'icons',
  'images',
  'audio'
]).forEach(dir => {
  content_paths.push('/content/'+dir)
  content_paths.push('/Content/'+dir)
})

module.exports = {
  mode: 'production',
  entry: path.join(__dirname, 'src/index.js'),
  output: {
    filename: 'app.js',
    path: path.join(__dirname, 'dist'),
  },
  module: {
    rules: [
      { test: /\.less$/, loader: 'style-loader!css-loader!less-loader' },
      {
        test: /\.css$/,
        loader: 'style-loader!css-loader'
      },
      {
          test: /\.jsx?$/,
          exclude: /node_modules/,
          loader: 'babel-loader',
      }
    ]
  },
  resolve: {
    alias: {
      '@components': path.join(__dirname, 'src/components/'),
      '@common': path.join(__dirname, 'src/common/')
    }
  },
  devServer: {
    contentBase: '.',
    historyApiFallback: true,

    proxy: [
      {
        context: ['/api', '/Reading', '/Scripts'].concat(content_paths),
        target: 'http://www.wordtraining.cn',
        changeOrigin: true,
        bypass: function (req, res, proxyOptions) {
          req.headers['Cookie'] = 'WordTrainingID=E26EA532CDE18D9914D7B6E0FF2B1F02B0F66E99AB8DB74BA152776E85E8BDF2761FC4AD6C6000B53DC1D8B94F95AC4CBD126635A67712D2C0B4536B57AC2AF27A3C6C1C58A39AEFE0F5534812EC572F53496103C5ADB12D8E3EAFA5C708CE5E; WordTrainingCode=44331DC2E58BA97E0D570719E151F7B75DDD8E91220EFE0FE62051278E05C9D0107C590A462336C02CC1F5106781A5E27615FFAA022B98CF1ABAF69D878ABF36B663FD2FA6ED7B8FD58FACF86297424ED5ED59FFC79B9FA249E188211A5CF1F8CCDAA021412F1C16A4DF2DA840447302A1E23647FC2E72A9F23FF220B6C676E0ED0D9B32DEEBB04E1F824865F0ED7D9ACC391BC40DF3400BD0B477D2E611BDABA07FD5CAE4222BC124D445FCA5A404B69B842D33A179B0A45E39B107393C374D61A2412E393A2961524EC3E8BEF25EB8607ADADA2DB1D1CF95A4882183E69DC8'
        }
      },
      {
        context: ['/Account/CheckUserName', '/Account/LoginIn', '/Account/Register'],
        target: 'http://www.wordtraining.cn',
        changeOrigin: true
      },
      {
        context: ['/UserCenter/UserCenter', '/UserCenter/Setting', '/UserCenter/UpdatePassword'],
        target: 'http://www.wordtraining.cn',
        changeOrigin: true,
        bypass: function (req, res, proxyOptions) {
          req.headers['Cookie'] = 'WordTrainingID=E26EA532CDE18D9914D7B6E0FF2B1F02B0F66E99AB8DB74BA152776E85E8BDF2761FC4AD6C6000B53DC1D8B94F95AC4CBD126635A67712D2C0B4536B57AC2AF27A3C6C1C58A39AEFE0F5534812EC572F53496103C5ADB12D8E3EAFA5C708CE5E; WordTrainingCode=44331DC2E58BA97E0D570719E151F7B75DDD8E91220EFE0FE62051278E05C9D0107C590A462336C02CC1F5106781A5E27615FFAA022B98CF1ABAF69D878ABF36B663FD2FA6ED7B8FD58FACF86297424ED5ED59FFC79B9FA249E188211A5CF1F8CCDAA021412F1C16A4DF2DA840447302A1E23647FC2E72A9F23FF220B6C676E0ED0D9B32DEEBB04E1F824865F0ED7D9ACC391BC40DF3400BD0B477D2E611BDABA07FD5CAE4222BC124D445FCA5A404B69B842D33A179B0A45E39B107393C374D61A2412E393A2961524EC3E8BEF25EB8607ADADA2DB1D1CF95A4882183E69DC8'
        }
      },
      {
        context: ['/soundfile'],
        target: 'http://voice.wordtraining.cn',
        changeOrigin: true,
        bypass: function (req, res, proxyOptions) {
          req.headers['Cookie'] = 'WordTrainingID=E26EA532CDE18D9914D7B6E0FF2B1F02B0F66E99AB8DB74BA152776E85E8BDF2761FC4AD6C6000B53DC1D8B94F95AC4CBD126635A67712D2C0B4536B57AC2AF27A3C6C1C58A39AEFE0F5534812EC572F53496103C5ADB12D8E3EAFA5C708CE5E; WordTrainingCode=44331DC2E58BA97E0D570719E151F7B75DDD8E91220EFE0FE62051278E05C9D0107C590A462336C02CC1F5106781A5E27615FFAA022B98CF1ABAF69D878ABF36B663FD2FA6ED7B8FD58FACF86297424ED5ED59FFC79B9FA249E188211A5CF1F8CCDAA021412F1C16A4DF2DA840447302A1E23647FC2E72A9F23FF220B6C676E0ED0D9B32DEEBB04E1F824865F0ED7D9ACC391BC40DF3400BD0B477D2E611BDABA07FD5CAE4222BC124D445FCA5A404B69B842D33A179B0A45E39B107393C374D61A2412E393A2961524EC3E8BEF25EB8607ADADA2DB1D1CF95A4882183E69DC8'
          req.headers.referer = 'http://www.wordtraining.cn/'
        }
      }
    ]
  }
}