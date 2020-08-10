const _ = require('lodash');

function getString(times) {
  return _.times(times, () => '🔔').join(" ");
}

exports.getString = getString;
