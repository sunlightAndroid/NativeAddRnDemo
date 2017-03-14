import { PropTypes } from 'react';
import { requireNativeComponent, View } from 'react-native';

var iface = {
  name: 'RCTMyPaintView',
  propTypes: {

    color: PropTypes.string,
    raduis: PropTypes.number,
    ...View.propTypes // 包含默认的View的属性
  },
};

module.exports = requireNativeComponent('RCTMyPaintView', iface);