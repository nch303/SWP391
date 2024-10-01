import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import NavBar from './component/header/layout';
import Home from './page/home/home';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import NotFound from './page/notfound/Notfound';
import ManagerKoi from './page/koiFish/manager-koi';
import Profile from './page/user/profile';
import ManagerPond from './page/koiPond/manager-pond';
import KoiInfo from './page/koiFish/koi-info';
import KoiStatistics from './page/statistics/koi-statistics';
import ShoppingRecommendations from './page/shop/recommendation';
import WaterParameter from './page/waterParameter/waterParameter';
import SaltCalculator from './page/saltCalculator/salt-calculator';
import FoodCalculator from './page/foodCalculator/food-calculator';
import ShopManager from './page/shop/manager-product';
const App = () => (
  <Router>
    <NavBar />
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/manage-koi" element={<ManagerKoi />} />
      <Route path="/manage-pond" element={<ManagerPond />} />
      <Route path="/water-parameters" element={<WaterParameter />} />
      <Route path="/calculate-food" element={<FoodCalculator />} />
      <Route path="/calculate-salt" element={<SaltCalculator />} />
      <Route path="/profile" element={<Profile />} />
      <Route path="/koi-info/:id" element={<KoiInfo />} />
      <Route path="/view-statistics" element={<KoiStatistics />} />
      <Route path="/shopping-recommendations" element={<ShoppingRecommendations />} />
      <Route path="/profile" element={<Profile />} />
      <Route path="/Shop-manager" element={<ShopManager />} />
      <Route path="*" element={<NotFound />} />
    </Routes>
  </Router>
);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
