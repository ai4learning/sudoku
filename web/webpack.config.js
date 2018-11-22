const path = require('path')
const webpack = require('webpack')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const CleanWebpackPlugin = require('clean-webpack-plugin')
const HtmlWebpackHarddiskPlugin = require('html-webpack-harddisk-plugin')
const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin

const cookie = 'WordTrainingID=6611098462F2F9E90B6BE1AE059032108D0EC48B994F1AED4BB7E92DCDE67C0B6EA7D8AA2E419E806715C60DF35F6D93F3EBB87BBFF67D7BFF191A417A55CDED1CCA31CEF116D70A37731F71321865B0D7067F90BC129F6DEBD609FFAB293615; WordTrainingCode=01C415E0D34A55DB44E2D09130BD0D82406B9D994ACC4EABFDE09E4E8268FC2836CF123E081C9FA8B62FB08EE93ECB1984088A0027F18A49DE5810ADC57C04DC6DA237A300F2000634309C00D439029DA850EBCE0A74FB0009F88E285B8B36AB13CEA9A55C90B38CC13D00427B2C756F476CFB07C5F67A00515F69CBF474FC4C43E0C47ABEF142955A3B52854D3D6A6A01C97B82EE5C93DFE0EB492019B081364E67FCE2137F9DB6FBF55B101815E9939B255AE939984C30C724F4A58649D267916CA11164E950B71B3C207D325B04B8E81C27B2334824C1650FC28B9453CDA8'

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
  entry: {
    app: path.join(__dirname, 'src/index.js'),
  },
  output: {
    filename: 'app.[hash:8].js',
    path: path.join(__dirname, 'dist'),
    publicPath: '/dist/'
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
        include: path.join(__dirname, 'src'),
        exclude: /node_modules/,
        loader: 'babel-loader?cacheDirectory',
      },
      {
        test: /\.(png|jpg|gif|svg)$/,
        loader: 'file-loader',
        options: {
          name: '[name].[ext]?[hash]'
        }
      }
    ]
  },
  externals: {
    "react": 'React',
    "react-dom": "ReactDOM",
    "moment": "moment",
    "antd": "antd",
    "bizcharts": "BizCharts",
    "@antv/data-set": "DataSet"
  },
  resolve: {
    alias: {
      '@src': path.join(__dirname, 'src'),
      '@components': path.join(__dirname, 'src/components/'),
      '@common': path.join(__dirname, 'src/common/')
    }
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: 'src/index.html',
      filename: '../index.html',
      alwaysWriteToDisk: true
    }),
    new HtmlWebpackPlugin({
      template: 'src/teacher.html',
      filename: '../teacher.html',
      alwaysWriteToDisk: true
    }),
    new HtmlWebpackHarddiskPlugin(),
    new CleanWebpackPlugin(['dist']),
    // new BundleAnalyzerPlugin()
  ],
  devServer: {
    contentBase: path.join(__dirname, './'),
    publicPath: '/dist/',
    proxy: [
      {
        context: ['/api', '/login'],
        target: 'http://47.106.116.148',
        changeOrigin: true
      },
      {
        context: ['/soundfile'],
        target: 'http://voice.wordtraining.cn',
        changeOrigin: true,
        bypass: function (req, res, proxyOptions) {
          req.headers['Cookie'] = cookie
          req.headers.referer = 'http://www.wordtraining.cn/'
        }
      }
    ]
  }
}