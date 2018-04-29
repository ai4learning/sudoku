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
    before(app) {
      apiMocker(app, path.resolve('./mock/index.js'))
    }
  }
}