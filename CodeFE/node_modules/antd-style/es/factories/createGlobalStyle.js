import _defineProperty from "@babel/runtime/helpers/esm/defineProperty";
function ownKeys(e, r) { var t = Object.keys(e); if (Object.getOwnPropertySymbols) { var o = Object.getOwnPropertySymbols(e); r && (o = o.filter(function (r) { return Object.getOwnPropertyDescriptor(e, r).enumerable; })), t.push.apply(t, o); } return t; }
function _objectSpread(e) { for (var r = 1; r < arguments.length; r++) { var t = null != arguments[r] ? arguments[r] : {}; r % 2 ? ownKeys(Object(t), !0).forEach(function (r) { _defineProperty(e, r, t[r]); }) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(t)) : ownKeys(Object(t)).forEach(function (r) { Object.defineProperty(e, r, Object.getOwnPropertyDescriptor(t, r)); }); } return e; }
import { Global } from '@emotion/react';
import { serializeStyles } from '@emotion/serialize';
import { memo } from 'react';
import { jsx as _jsx } from "react/jsx-runtime";
/**
 * 创建全局样式
 */
export var createGlobalStyleFactory = function createGlobalStyleFactory(useTheme) {
  return function () {
    for (var _len = arguments.length, styles = new Array(_len), _key = 0; _key < _len; _key++) {
      styles[_key] = arguments[_key];
    }
    return /*#__PURE__*/memo(function (props) {
      var theme = useTheme();
      return /*#__PURE__*/_jsx(Global, {
        styles: serializeStyles(styles, undefined, _objectSpread(_objectSpread({}, props), {}, {
          theme: theme
        }))
      });
    });
  };
};