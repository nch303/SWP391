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
    { link: '/manage-koi', image: '/images/koi-icon.png', text: 'Manage Koi', subtext: 'Managing: N/A Kois' },
    { link: '/manage-pond', image: '/images/pond-icon.png', text: 'Manage Pond', subtext: 'Managing: N/A Ponds' },
    { link: '/water-parameters', image: '/images/water-icon.png', text: 'Water Parameters', subtext: 'Last Entry: N/A' },
    { link: '/set-reminders', image: '/images/reminder-icon.png', text: 'Set Reminders', subtext: 'N/A Reminders set for today' },
    { link: '/calculate-food', image: '/images/food-icon.png', text: 'Calculate Food', subtext: 'Add Flavor text' },
    { link: '/calculate-salt', image: '/images/salt-icon.png', text: 'Calculate Salt', subtext: 'Add Flavor text' },
    { link: '/view-statistics', image: '/images/stats-icon.png', text: 'View Statistics', subtext: 'Add Flavor text' },
    { link: '/shopping-recommendations', image: '/images/shopping-icon.png', text: 'Shopping Recommendations', subtext: 'Add Flavor text' },
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