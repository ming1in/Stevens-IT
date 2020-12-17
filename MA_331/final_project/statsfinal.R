taste <- c(12.3, 20.9, 39.0, 47.9, 5.6, 25.9, 37.3, 21.9, 18.1, 21.0, 34.9, 57.2, 0.7, 25.9, 54.9, 40.9, 15.9, 6.4, 18.0, 38.9, 14.0, 15.2, 32.0, 56.7, 16.8, 11.6, 26.5, 0.7, 13.4, 5.5)
acetic <- c(4.543, 5.159, 5.366, 5.759, 4.663, 5.697, 5.892, 6.078, 4.898, 5.242, 5.740, 6.446, 4.477, 5.236, 6.151, 6.365, 4.787, 5.412, 5.247, 5.438, 4.564, 5.298, 5.455, 5.855, 5.366, 6.043, 6.458, 5.328, 5.802, 6.176)
h2s <- c(3.135, 5.043, 5.438, 7.496, 3.807, 7.601, 8.726, 7.966, 3.850, 4.174, 6.142, 7.908, 2.996, 4.942, 6.752, 9.588, 3.912, 4.700, 6.174, 9.064, 4.949, 5.220, 9.242, 10.199, 3.664, 3.219, 6.962, 3.912, 6.685, 4.787)
lactic <- c(0.86, 1.53, 1.57, 1.81, 0.99, 1.09, 1.29, 1.78, 1.29, 1.58, 1.68, 1.90, 1.06, 1.30, 1.52, 1.74, 1.16, 1.49, 1.63, 1.99, 1.15, 1.33, 1.44, 2.01, 1.31, 1.46, 1.72, 1.25, 1.08, 1.25)

#11.53
summary(taste)
summary(acetic)
summary(h2s)
summary(lactic)

sd(taste)
sd(acetic)
sd(h2s)
sd(lactic)

IQR(taste)
IQR(acetic)
IQR(h2s)
IQR(lactic)

stem(taste)
stem(acetic)
stem(h2s)
stem(lactic)

qqnorm(taste, main = "Taste")
qqnorm(acetic, main = "Acetic")
qqnorm(h2s, main = "h2s")
qqnorm(lactic, main = "Lactic")

#11.54
taste_acetic <- data.frame(taste, acetic)
taste_h2s <- data.frame(taste, h2s)
taste_lactic <- data.frame(taste, lactic)
acetic_h2s <- data.frame(acetic, h2s)
acetic_lactic <- data.frame(acetic, lactic)
h2s_lactic <- data.frame(h2s, lactic)

plot(taste_acetic, main = "Taste vs Acetic")
plot(taste_h2s, main = "Taste vs H2S")
plot(taste_lactic, main = "Taste vs Lactic")
plot(acetic_h2s, main = "Acetic vs H2S")
plot(acetic_lactic, main = "Acetic vs Lactic")
plot(h2s_lactic, main = "H2S vs Lactic")

cor(taste_acetic)
cor(taste_h2s)
cor(taste_lactic)
cor(acetic_h2s)
cor(acetic_lactic)
cor(h2s_lactic)

cor.test(taste, acetic)
cor.test(taste, h2s)
cor.test(taste, lactic)
cor.test(acetic,h2s)
cor.test(acetic, lactic)
cor.test(h2s, lactic)

#11.55
taste_acetic_linmod <- lm(taste~acetic, data = taste_acetic)
plot(acetic, taste, main = "Acetic vs Taste")
abline(taste_acetic_linmod)
summary(taste_acetic_linmod)
plot(taste_acetic_linmod)
res <- resid(taste_acetic_linmod)

plot(h2s, res, ylab="Residuals", xlab="h2s", main="Residual Plot for h2s")
plot(lactic, res, ylab="Residuals", xlab="lactic", main="Residual Plot for lactic")

#11.56
taste_h2s_linmod <- lm(taste~h2s, data = taste_h2s)
plot(h2s, taste, main = "H2S vs Taste")
abline(taste_h2s_linmod)
summary(taste_h2s_linmod)
plot(taste_h2s_linmod)
res2 <- resid(taste_h2s_linmod)

plot(acetic, res2, ylab="Residuals", xlab="acetic", main="Residual Plot for acetic")
plot(lactic, res2, ylab="Residuals", xlab="lactic", main="Residual Plot for lactic")

#11.57 
taste_lactic_linmod <- lm(taste~lactic, data = taste_lactic)
plot(lactic, taste, main = "Lactic vs Taste")
abline(taste_lactic_linmod)
summary(taste_lactic_linmod)
plot(taste_lactic_linmod)
res3 <- resid(taste_lactic_linmod)

plot(acetic, res3, ylab="Residuals", xlab="acetic", main="Residual Plot for acetic")
plot(h2s, res3, ylab="Residuals", xlab="h2s", main="Residual Plot for h2s")

#11.59
fit <- lm(taste ~ acetic + h2s)
summary(fit)
plot(fit)

#11.60
fit <- lm(taste ~ h2s + lactic)
summary(fit)
plot(fit)

#11.61
fit <- lm(taste ~ acetic + h2s + lactic)
summary(fit)
plot(fit)