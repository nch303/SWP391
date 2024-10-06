"use strict";

var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");
Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.createGlobalStyleFactory = void 0;
var _defineProperty2 = _interopRequireDefault(require("@babel/runtime/helpers/defineProperty"));
var _react = require("@emotion/react");
var _serialize = require("@emotion/serialize");
var _react2 = require("react");
var _jsxRuntime = require("react/jsx-runtime");
function ownKeys(e, r) { var t = Object.keys(e); if (Object.getOwnPropertySymbols) { var o = Object.getOwnPropertySymbols(e); r && (o = o.filter(function (r) { return Object.getOwnPropertyDescriptor(e, r).enumerable; })), t.push.apply(t, o); } return t; }
function _objectSpread(e) { for (var r = 1; r < arguments.length; r++) { var t = null != arguments[r] ? arguments[r] : {}; r % 2 ? ownKeys(Object(t), !0).forEach(function (r) { (0, _defineProperty2.default)(e, r, t[r]); }) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(t)) : ownKeys(Object(t)).forEach(function (r) { Object.defineProperty(e, r, Object.getOwnPropertyDescriptor(t, r)); }); } return e; }
/**
 * 创建全局样式
 */
var createGlobalStyleFactory = exports.createGlobalStyleFactory = function createGlobalStyleFactory(useTheme) {
  return function () {
    for (var _len = arguments.length, styles = new Array(_len), _key = 0; _key < _len; _key++) {
      styles[_key] = arguments[_key];
    }
    return /*#__PURE__*/(0, _react2.memo)(function (props) {
      var theme = useTheme();
      return /*#__PURE__*/(0, _jsxRuntime.jsx)(_react.Global, {
        styles: (0, _serialize.serializeStyles)(styles, undefined, _objectSpread(_objectSpread({}, props), {}, {
          theme: theme
        }))
      });
    });
  };
};