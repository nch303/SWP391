import React from 'react';
import './home.css';

const DashboardItem = ({ link, image, text, subtext }) => (
  <a href={link} className="dashboard-item">
    <div className="icon">
      <img src={image} alt="" />
    </div>
    <div className="text">
      <h3>{text}</h3>
      <p>{subtext}</p>
    </div>
  </a>
);

export default function Home() {  
  const buttons = [
      { link: '/manage-koi', image: '8541961_fish_fishing_icon.png', text: 'Manage Koi', subtext: 'Managing: N/A Kois' },
      { link: '/manage-pond', image: 'waves_24dp_000000_FILL0_wght400_GRAD0_opsz24.png', text: 'Manage Pond', subtext: 'Managing: N/A Ponds' },
      { link: '/water-parameters', image: 'invert_colors_24dp_000000_FILL0_wght400_GRAD0_opsz24.png', text: 'Water Parameters', subtext: 'Last Entry: N/A' },
      { link: '/set-reminders', image: 'timer_24dp_000000_FILL0_wght400_GRAD0_opsz24.png', text: 'Set Reminders', subtext: 'N/A Reminders set for today' },
      { link: '/calculate-food', image: 'restaurant_24dp_000000_FILL0_wght400_GRAD0_opsz24.png', text: 'Calculate Food', subtext: 'Add Flavor text' },
      { link: '/calculate-salt', image: 'salinity_24dp_000000_FILL0_wght400_GRAD0_opsz24.png', text: 'Calculate Salt', subtext: 'Add Flavor text' },
      { link: '/view-statistics', image: 'monitoring_24dp_000000_FILL0_wght400_GRAD0_opsz24.png', text: 'View Statistics', subtext: 'Add Flavor text' },
      { link: '/shopping-recommendations', image: 'shopping_cart_24dp_000000_FILL0_wght400_GRAD0_opsz24.png', text: 'Shopping Recommendations', subtext: 'Add Flavor text' },
  ];

  return (
    <div className="dashboard">
      {buttons.map((button, index) => (
        <DashboardItem
          key={index}
          link={button.link}
          image={button.image}
          text={button.text}
          subtext={button.subtext}
        />
      ))}
    </div>
  );
}