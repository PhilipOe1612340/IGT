const CompressionPlugin = require("compression-webpack-plugin")

module.exports = {
  productionSourceMap: false,
  lintOnSave: undefined,
  configureWebpack: {
    plugins: [
      new CompressionPlugin({
        asset: "[path].gz[query]",
        algorithm: "gzip",
        test: /\.js$|\.css$|\.html$/,
        threshold: 10240,
        minRatio: 0.8
      })
    ]
  }
};
