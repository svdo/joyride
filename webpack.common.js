//webpack.config.js
const path = require('path');

module.exports = {
  target: 'electron18.1-main',
  entry: {
    main: "./src/extension.js",
  },
  output: {
    path: path.resolve(__dirname, '.'),
    filename: "extension.js" // <--- Will be compiled to this single file
  },
  resolve: {
    extensions: [".js"],
  },
  externals: {
    vscode: 'commonjs vscode',
  }
};
