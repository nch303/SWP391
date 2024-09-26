import React, { useState } from 'react';

const initialProducts = [
    { id: 1, name: 'Koi Fish A', price: 50, image: 'link_to_image_a', description: 'Description A', link: 'link_a' },
    { id: 2, name: 'Koi Fish B', price: 70, image: 'link_to_image_b', description: 'Description B', link: 'link_b' },
];

const ProductManager = () => {
    const [products, setProducts] = useState(initialProducts);
    const [form, setForm] = useState({ id: null, name: '', price: '', image: '', description: '', link: '' });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setForm({ ...form, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (form.id) {
            // Update existing product
            setProducts(products.map(product => (product.id === form.id ? form : product)));
        } else {
            // Create new product
            setProducts([...products, { ...form, id: Date.now() }]);
        }
        setForm({ id: null, name: '', price: '', image: '', description: '', link: '' });
    };

    const handleEdit = (product) => {
        setForm(product);
    };

    const handleDelete = (id) => {
        setProducts(products.filter(product => product.id !== id));
    };

    const handlePostProduct = () => {
        // Assuming there's a function to post a product to the database or API
        // This is a placeholder for the actual logic to post a product
        console.log('Post Product functionality is not implemented yet.');
    };

    return (
        <div>
            <h1>Product Manager</h1>
            <form onSubmit={handleSubmit}>
                <input name="name" value={form.name} onChange={handleChange} placeholder="Product Name" required />
                <input name="price" value={form.price} onChange={handleChange} placeholder="Price" required />
                <input name="image" value={form.image} onChange={handleChange} placeholder="Image URL" required />
                <input name="description" value={form.description} onChange={handleChange} placeholder="Description" required />
                <input name="link" value={form.link} onChange={handleChange} placeholder="Product Link" required />
                <button type="submit">{form.id ? 'Update' : 'Add'} Product</button>
                <button onClick={handlePostProduct}>Post Product</button>
            </form>
            <div className="product-list">
                {products.map(product => (
                    <div key={product.id} className="product-card">
                        <img src={product.image} alt={product.name} />
                        <h2>{product.name}</h2>
                        <p>Price: ${product.price}</p>
                        <p>{product.description}</p>
                        <a href={product.link}>View Product</a>
                        <button onClick={() => handleEdit(product)}>Edit</button>
                        <button onClick={() => handleDelete(product.id)}>Delete</button>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ProductManager;
