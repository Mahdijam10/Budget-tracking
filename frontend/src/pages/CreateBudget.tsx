import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Plus, X } from "lucide-react";

const CreateBudget = () => {
    const navigate = useNavigate();
    const [name, setName] = useState("");
    const [category, setCategory] = useState("");
    const [budgetLimit, setBudgetLimit] = useState("");
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [userId] = useState(1); // replace with actual user ID after login

    const [expenses, setExpenses] = useState([{ amount: "", source: "", date: "" }]);
    const [incomes, setIncomes] = useState([{ amount: "", description: "", date: "", category: "" }]);

    const handleAddExpense = () => setExpenses([...expenses, { amount: "", source: "", date: "" }]);
    const handleAddIncome = () => setIncomes([...incomes, { amount: "", description: "", date: "", category: "" }]);

    const handleExpenseChange = (i, field, value) => {
        const updated = expenses.map((e, idx) => idx === i ? { ...e, [field]: value } : e);
        setExpenses(updated);
    };

    const handleIncomeChange = (i, field, value) => {
        const updated = incomes.map((inc, idx) => idx === i ? { ...inc, [field]: value } : inc);
        setIncomes(updated);
    };

    const removeExpense = (i) => setExpenses(expenses.filter((_, idx) => idx !== i));
    const removeIncome = (i) => setIncomes(incomes.filter((_, idx) => idx !== i));

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            await axios.post("http://localhost:8089/api/budgets", {
                name,
                category,
                budgetLimit,
                startDate,
                endDate,
                userId,
                expenseDTOs: expenses.map((e) => ({ ...e, budgetId: null })),
                incomeDTOs: incomes.map((i) => ({ ...i, budgetId: null })),
            });
            navigate("/dashboard");
        } catch (err) {
            console.error("Failed to create budget:", err);
        }
    };

    return (
        <div className="max-w-3xl mx-auto py-10 px-4">
            <Card>
                <CardHeader>
                    <CardTitle>Create Budget</CardTitle>
                </CardHeader>
                <CardContent>
                    <form onSubmit={handleSubmit} className="space-y-6">
                        <div className="grid md:grid-cols-2 gap-4">
                            <div>
                                <Label>Budget Name</Label>
                                <Input value={name} onChange={(e) => setName(e.target.value)} required />
                            </div>
                            <div>
                                <Label>Category</Label>
                                <Input value={category} onChange={(e) => setCategory(e.target.value)} required />
                            </div>
                        </div>

                        <div className="grid md:grid-cols-2 gap-4">
                            <div>
                                <Label>Limit ($)</Label>
                                <Input type="number" value={budgetLimit} onChange={(e) => setBudgetLimit(e.target.value)} required />
                            </div>
                            <div>
                                <Label>Start Date</Label>
                                <Input type="date" value={startDate} onChange={(e) => setStartDate(e.target.value)} required />
                            </div>
                            <div>
                                <Label>End Date</Label>
                                <Input type="date" value={endDate} onChange={(e) => setEndDate(e.target.value)} required />
                            </div>
                        </div>

                        <div className="pt-6">
                            <Label className="text-lg">Expenses</Label>
                            {expenses.map((exp, i) => (
                                <div key={i} className="grid md:grid-cols-4 gap-2 items-center mb-2">
                                    <Input type="number" placeholder="Amount" value={exp.amount} onChange={(e) => handleExpenseChange(i, "amount", e.target.value)} required />
                                    <Input placeholder="Source" value={exp.source} onChange={(e) => handleExpenseChange(i, "source", e.target.value)} required />
                                    <Input type="date" value={exp.date} onChange={(e) => handleExpenseChange(i, "date", e.target.value)} required />
                                    <Button type="button" variant="ghost" onClick={() => removeExpense(i)}><X className="w-4 h-4" /></Button>
                                </div>
                            ))}
                            <Button type="button" variant="outline" onClick={handleAddExpense} className="mt-2"><Plus className="w-4 h-4 mr-1" /> Add Expense</Button>
                        </div>

                        <div className="pt-6">
                            <Label className="text-lg">Incomes</Label>
                            {incomes.map((inc, i) => (
                                <div key={i} className="grid md:grid-cols-4 gap-2 items-center mb-2">
                                    <Input type="number" placeholder="Amount" value={inc.amount} onChange={(e) => handleIncomeChange(i, "amount", e.target.value)} required />
                                    <Input placeholder="Description" value={inc.description} onChange={(e) => handleIncomeChange(i, "description", e.target.value)} required />
                                    <Input type="date" value={inc.date} onChange={(e) => handleIncomeChange(i, "date", e.target.value)} required />
                                    <Input placeholder="Category" value={inc.category} onChange={(e) => handleIncomeChange(i, "category", e.target.value)} />
                                    <Button type="button" variant="ghost" onClick={() => removeIncome(i)}><X className="w-4 h-4" /></Button>
                                </div>
                            ))}
                            <Button type="button" variant="outline" onClick={handleAddIncome} className="mt-2"><Plus className="w-4 h-4 mr-1" /> Add Income</Button>
                        </div>

                        <Button type="submit" className="w-full bg-green-600 hover:bg-green-700">Create Budget</Button>
                    </form>
                </CardContent>
            </Card>
        </div>
    );
};

export default CreateBudget;
