const vscode = require("vscode");
const congas = import('./out/congas.js');

/**
 * @param {vscode.ExtensionContext} context
 */
async function activate(context) {
  const c = await congas;
  c.activate(context);
}

async function deactivate() {
  const c = await congas;
  c.deactivate();
}

module.exports = {
  activate,
  deactivate,
};
